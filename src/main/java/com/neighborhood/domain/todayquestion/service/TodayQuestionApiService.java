package com.neighborhood.domain.todayquestion.service;

import com.neighborhood.domain.family.entity.Family;
import com.neighborhood.domain.family.entity.FamilyTypeScore;
import com.neighborhood.domain.family.repository.FamilyTypeScoreRepository;
import com.neighborhood.domain.family.service.FamilyApiService;
import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.pretest.entity.TestType;
import com.neighborhood.domain.todayquestion.repository.TodayQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodayQuestionApiService {

    private final FamilyTypeScoreRepository familyTypeScoreRepository;
    private final TodayQuestionRepository todayQuestionRepository;
    private final FamilyApiService familyApiService;

    @Transactional
    public Boolean checkUpdate(Member member) {

        LocalDateTime questionUpdatedTime = member.getFamily().getQuestionUpdatedTime();
        familyApiService.updateFamilyTypeScore(member.getFamily());

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
