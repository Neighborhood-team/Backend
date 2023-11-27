package com.neighborhood.domain.todayquestion.service;

import com.neighborhood.domain.family.entity.Family;
import com.neighborhood.domain.family.entity.FamilyTypeScore;
import com.neighborhood.domain.family.repository.FamilyTypeScoreRepository;
import com.neighborhood.domain.family.service.FamilyApiService;
import com.neighborhood.domain.firebase.FCMNotificationRequestDto;
import com.neighborhood.domain.firebase.FCMService;
import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.pretest.entity.Result;
import com.neighborhood.domain.pretest.entity.TestType;
import com.neighborhood.domain.todayquestion.dto.TodayQuestionDto;
import com.neighborhood.domain.todayquestion.entity.TodayQuestion;
import com.neighborhood.domain.todayquestion.entity.TodayQuestionAnswer;
import com.neighborhood.domain.todayquestion.repository.TodayQuestionAnswerRepository;
import com.neighborhood.domain.todayquestion.repository.TodayQuestionRepository;
import com.neighborhood.global.dto.MessageOnlyResponseDto;
import com.neighborhood.global.exception.RestApiException;
import com.neighborhood.global.exception.errorCode.CommonErrorCode;
import com.neighborhood.global.exception.errorCode.TodayQuestionErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodayQuestionApiService {

    private final TodayQuestionRepository todayQuestionRepository;
    private final TodayQuestionAnswerRepository todayQuestionAnswerRepository;
    private final FamilyTypeScoreRepository familyTypeScoreRepository;
    private final FCMService fcmService;

    /**
     * 해당 회원 가족의 오늘의 질문 최신화 시간을 확인하고 오늘 이전이면 오늘의 질문 갱신
     * @param member 해당 회원 엔티티
     * @return 갱신되었으면 true, 아니면 false
     */
    @Transactional
    public Boolean checkUpdate(Member member) {

        LocalDateTime questionUpdatedTime = member.getFamily().getQuestionUpdatedTime();

        // TODO: 테스트용으로 추가해놨음. 운영 시에는 해당 메소드 호출 필요 없음
        updateFamilyTypeScore(member.getFamily());

        if (questionUpdatedTime == null) {
            updateTodayQuestion(member);
            return true;
        }

        LocalDateTime todayStart = LocalDate.now().atStartOfDay();
        boolean isBeforeToday = questionUpdatedTime.isBefore(todayStart);

        if (isBeforeToday) {
            updateTodayQuestion(member);
            return true;
        }
        return false;
    }

    /**
     * 해당 회원 가족의 오늘의 질문을 조회
     * @param member 해당 회원 엔티티
     * @return 오늘의 질문 dto
     */
    @Transactional
    public TodayQuestionDto.TodayQuestion getTodayQuestion(Member member) {

        TestType todayQuestionType = member.getFamily().getTodayQuestionType();
        Integer questionNum = member.getFamily().getQuestionNum();

        TodayQuestion todayQuestion = todayQuestionRepository
                .findAllByTypeOrderByQuestionIdAsc(todayQuestionType).get(questionNum);

        TodayQuestionAnswer todayQuestionAnswer =
                todayQuestionAnswerRepository.findByMemberAndCreatedDate(member, LocalDate.now()).orElse(null);
        Boolean isAnswered = todayQuestionAnswer != null;

        return new TodayQuestionDto.TodayQuestion(
                todayQuestion.getQuestionId(), todayQuestion.getContent(), todayQuestion.getSubText(), isAnswered);
    }

    @Transactional
    public ResponseEntity<?> addAnswer(Member member, TodayQuestionDto.AnswerForm body) {

        TodayQuestion question = todayQuestionRepository
                .findById(body.getQuestionId())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));

        if(todayQuestionAnswerRepository.findByMemberAndCreatedDate(member, LocalDate.now()).orElse((null)) != null){
            throw new RestApiException(TodayQuestionErrorCode.DUPLICATED_ANSWER_REQUEST);
        }

        TodayQuestionAnswer answer =
                new TodayQuestionAnswer(body.getContent(), LocalDate.now(), question, member.getFamily(), member);
        todayQuestionAnswerRepository.save(answer);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @Transactional
    public TodayQuestionDto.AnswersOfFamily getFamilyAnswers(Member member, LocalDate date) {

        Family family = member.getFamily();
        List<TodayQuestionAnswer> answers = todayQuestionAnswerRepository.findAllByFamilyAndCreatedDate(family, date);
        List<TodayQuestionDto.AnswerOfMember> answerOfMembers = new ArrayList<>();

        for (Member m : family.getMembers().stream()
                .sorted(Comparator.comparing(Member::getBirthDate))
                .collect(Collectors.toList())) {
            TodayQuestionAnswer answer = todayQuestionAnswerRepository.findByMemberAndCreatedDate(m, date).orElse(null);
            Boolean isAnswered = answer != null;
            Long answerId = null;
            String content = null;

            if (isAnswered){
                answerId = answer.getAnswerId();
                content = answer.getContent();
            }
            answerOfMembers.add(
                    new TodayQuestionDto.AnswerOfMember(
                            isAnswered, m.getMemberId(), Objects.equals(m.getMemberId(), member.getMemberId()),
                            m.getFamilyRole(), m.getName(), answerId, content));
        }

        return answers.size() == 0 ?
                new TodayQuestionDto.AnswersOfFamily(
                        false, null, "이 날에는 아무도 답변하지 않았습니다.", answerOfMembers) :
                new TodayQuestionDto.AnswersOfFamily(
                        true, answers.get(0).getTodayQuestion().getQuestionId(), answers.get(0).getTodayQuestion().getContent(), answerOfMembers);

    }




    public void updateTodayQuestion(Member member) {

        Family family = member.getFamily();
        List<FamilyTypeScore> familyTypeScores = family.getFamilyTypeScores();
        familyTypeScores.sort((o1, o2) -> o2.getScore().compareTo(o1.getScore()));
        for (FamilyTypeScore familyTypeScore : familyTypeScores) {
            log.info(familyTypeScore.getTestType() + ": " + familyTypeScore.getScore());
        }

        // 처음 오늘의 질문을 갱신해야하는 가족의 경우
        if(family.getTodayQuestionType() == null) {
            family.setTodayQuestionType(familyTypeScores.get(0).getTestType());
            family.setQuestionNum(0);
            family.setQuestionUpdateTime(LocalDateTime.now());
            return ;
        }

        int currTypeIndex = getCurrTypeIndex(familyTypeScores, family.getTodayQuestionType());

        // 현재 질문이 현재 유형의 마지막 질문일 때
        if (family.getQuestionNum() + 1 == todayQuestionRepository.countAllByType(family.getTodayQuestionType())) {
            //현재 유형이 마지막 유형일 때
            if (currTypeIndex + 1 == familyTypeScores.size()) {
                family.setTodayQuestionType(familyTypeScores.get(0).getTestType());
                family.setQuestionNum(0);
                family.setQuestionUpdateTime(LocalDateTime.now());
            } else {
                family.setTodayQuestionType(familyTypeScores.get(currTypeIndex+1).getTestType());
                family.setQuestionNum(0);
                family.setQuestionUpdateTime(LocalDateTime.now());
            }

            return ;
        }

        // 현재 질문이 현재 유형의 마지막 질문도 아니고 현재 유형도 마지막 유형이 아닐 때
        family.setQuestionNum(family.getQuestionNum()+1);
        family.setQuestionUpdateTime(LocalDateTime.now());

    }


    public MessageOnlyResponseDto sendAnswerRequestPush(Long memberId) {

        FCMNotificationRequestDto fcmDto = FCMNotificationRequestDto.builder()
                .targetId(memberId)
                .title("공통 질문 답변 요청")
                .body("가족이 오늘의 질문에 대해 궁금해해요!")
                .build();

        fcmService.sendNotification(fcmDto);

        return new MessageOnlyResponseDto("답변요청 푸시 알림 전송 성공");
    }


    /**
     * 가족의 유형별 점수 총합을 계산하고 반영함
     * @param family 대상 가족 엔티티
     */
    public void updateFamilyTypeScore(Family family) {

        int strongTotal = 0;
        int awkwardTotal = 0;
        int lostTotal = 0;
        int frozenTotal = 0;
        int thirstyTotal = 0;
        int confusedTotal = 0;
        int hiddenTotal = 0;

        // 한 가족의 회원 모두 가져옴
        List<Member> members = family.getMembers();

        // 가족 구성원들을 하나씩 순회하면서 각 유형 총합 변수에 더함
        for (Member member : members) {
            Result result = member.getResult();
            if (result == null) {
                continue;
            }

            strongTotal += result.getTypeScores().get(TestType.STRONG);
            awkwardTotal += result.getTypeScores().get(TestType.AWKWARD);
            lostTotal += result.getTypeScores().get(TestType.LOST);
            frozenTotal += result.getTypeScores().get(TestType.FROZEN);
            thirstyTotal += result.getTypeScores().get(TestType.THIRSTY);
            confusedTotal += result.getTypeScores().get(TestType.CONFUSED);
            hiddenTotal += result.getTypeScores().get(TestType.HIDDEN);
        }

        // 새롭게 구한 유형별 총합을 갱신함
        familyTypeScoreRepository.findByFamilyAndTestType(family, TestType.STRONG)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND)).setScore(strongTotal);
        familyTypeScoreRepository.findByFamilyAndTestType(family, TestType.AWKWARD)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND)).setScore(awkwardTotal);
        familyTypeScoreRepository.findByFamilyAndTestType(family, TestType.LOST)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND)).setScore(lostTotal);
        familyTypeScoreRepository.findByFamilyAndTestType(family, TestType.FROZEN)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND)).setScore(frozenTotal);
        familyTypeScoreRepository.findByFamilyAndTestType(family, TestType.THIRSTY)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND)).setScore(thirstyTotal);
        familyTypeScoreRepository.findByFamilyAndTestType(family, TestType.CONFUSED)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND)).setScore(confusedTotal);
        familyTypeScoreRepository.findByFamilyAndTestType(family, TestType.HIDDEN)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND)).setScore(hiddenTotal);
    }


    /**
     * 현재 오늘의 질문 타입이 몇번째 타입인지 반환하는 내부 메서드
     * @param typeScores 내림차순 정렬된 가족의 유형별 점수 총합 리스트
     * @param type 위치를 확인하고 싶은 유형
     * @return
     */
    private int getCurrTypeIndex(List<FamilyTypeScore> typeScores, TestType type) {
        for (int i = 0; i < typeScores.size(); i++) {
            if (typeScores.get(i).getTestType() == type) {
                return i;
            }
        }
        return -1;
    }
}
