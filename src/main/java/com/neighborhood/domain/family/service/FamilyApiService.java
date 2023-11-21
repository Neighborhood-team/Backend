package com.neighborhood.domain.family.service;

import com.neighborhood.domain.family.entity.Family;
import com.neighborhood.domain.family.entity.FamilyTypeScore;
import com.neighborhood.domain.family.repository.FamilyRepository;
import com.neighborhood.domain.family.repository.FamilyTypeScoreRepository;
import com.neighborhood.domain.member.dto.MemberResponseDto;
import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.service.MemberManageService;
import com.neighborhood.domain.pretest.entity.TestType;
import com.neighborhood.domain.todayquestion.service.TodayQuestionApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FamilyApiService {

    private final FamilyRepository familyRepository;
    private final FamilyTypeScoreRepository familyTypeScoreRepository;
    private final TodayQuestionApiService todayQuestionApiService;
    private final MemberManageService memberManageService;

    /**
     * 해당 회원을 이미 존재하는 가족에 추가
     * @param memberId 사용자 PK
     * @param familyCode 가족 고유 코드
     * @return True: 정상적으로 추가 완료 / False: 가족코드에 해당하는 가족 엔티티 조회 불가
     */
    @Transactional
    public Boolean addMemberToExistingFamily(Long memberId, String familyCode) {

        // 가족 코드를 이용하여 존재하는 가족 엔티티 가져오기
        Family family = familyRepository.findByFamilyCode(familyCode).orElse(null);

        if (family == null) {
            return false;
        }

        // 조회된 가족을 해당 회원의 가족으로 설정
        Member member = memberManageService.findMember(memberId);
        member.setFamily(family);

        // 추가된 회원을 포함하여 가족 유형 점수 총합 최신화 및 오늘의 질문 사이클 다시 시작
        todayQuestionApiService.updateFamilyTypeScore(family);
        todayQuestionApiService.updateTodayQuestion(member);

        return true;
    }


    /**
     * 새 가족을 만들고 해당 회원을 해당 가족에 추가. 가족의 유형별 총합을 0으로 초기화.
     * @param memberId 사용자 PK
     */
    @Transactional
    public MemberResponseDto addMemberToNewFamily(Long memberId) {

        // 새 가족 엔티티 생성
        Family family = new Family();
        familyRepository.save(family);

        // 생성된 가족에 해당 회원 추가
        Member member = memberManageService.findMember(memberId);
        member.setFamily(family);

        // 만들어진 가족의 유형 총점을 0으로 초기화하여 각 유형별 가족 유형점수 총합 엔티티 생성
        FamilyTypeScore familyTypeScore1 = new FamilyTypeScore(TestType.STRONG, family);
        FamilyTypeScore familyTypeScore2 = new FamilyTypeScore(TestType.AWKWARD, family);
        FamilyTypeScore familyTypeScore3 = new FamilyTypeScore(TestType.LOST, family);
        FamilyTypeScore familyTypeScore4 = new FamilyTypeScore(TestType.FROZEN, family);
        FamilyTypeScore familyTypeScore5 = new FamilyTypeScore(TestType.THIRSTY, family);
        FamilyTypeScore familyTypeScore6 = new FamilyTypeScore(TestType.CONFUSED, family);
        FamilyTypeScore familyTypeScore7 = new FamilyTypeScore(TestType.HIDDEN, family);
        familyTypeScoreRepository.saveAll(List.of(
                familyTypeScore1,
                familyTypeScore2,
                familyTypeScore3,
                familyTypeScore4,
                familyTypeScore5,
                familyTypeScore6,
                familyTypeScore7));

        // 해당 회원을 바탕으로 가족 유형 점수 총합 최신화 및 오늘의 질문 사이클 시작
        todayQuestionApiService.updateFamilyTypeScore(family);
        todayQuestionApiService.updateTodayQuestion(member);

        return new MemberResponseDto(member);


    }


}
