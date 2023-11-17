package com.neighborhood.domain.family.service;

import com.neighborhood.domain.family.entity.Family;
import com.neighborhood.domain.family.entity.FamilyTypeScore;
import com.neighborhood.domain.family.repository.FamilyRepository;
import com.neighborhood.domain.family.repository.FamilyTypeScoreRepository;
import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import com.neighborhood.domain.pretest.entity.Result;
import com.neighborhood.domain.pretest.entity.TestType;
import com.neighborhood.global.exception.RestApiException;
import com.neighborhood.global.exception.errorCode.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FamilyApiService {

    private final FamilyRepository familyRepository;
    private final MemberRepository memberRepository;
    private final FamilyTypeScoreRepository familyTypeScoreRepository;

    /**
     * 해당 회원을 이미 존재하는 가족에 추가
     * @param member Member 엔티티
     * @param familyCode 가족 고유 코드
     * @return True: 정상적으로 추가 완료 / False: 가족코드에 해당하는 가족 엔티티 조회 불가
     */
    @Transactional
    public Boolean addMemberToExistingFamily(Member member, String familyCode) {

        Family family = familyRepository.findByFamilyCode(familyCode).orElse(null);

        if (family == null) {
            return false;
        }

        member.setFamily(family);
        memberRepository.save(member);

        updateFamilyTypeScore(family);

        return true;
    }


    /**
     * 새 가족을 만들고 해당 회원을 해당 가족에 추가. 가족의 유형별 총합을 0으로 초기화.
     * @param member
     */
    @Transactional
    public void addMemberToNewFamily(Member member) {

        Family family = new Family();
        familyRepository.save(family);

        member.setFamily(family);
        memberRepository.save(member);

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

        updateFamilyTypeScore(family);

    }

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
}
