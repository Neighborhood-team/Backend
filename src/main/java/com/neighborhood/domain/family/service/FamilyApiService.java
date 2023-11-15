package com.neighborhood.domain.family.service;

import com.neighborhood.domain.family.entity.Family;
import com.neighborhood.domain.family.entity.FamilyTypeScore;
import com.neighborhood.domain.family.repository.FamilyRepository;
import com.neighborhood.domain.family.repository.FamilyTypeScoreRepository;
import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import com.neighborhood.domain.pretest.entity.Result;
import com.neighborhood.domain.pretest.entity.TestType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
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
    public Boolean addMemberToExistingFamily(Member member, String familyCode) {

        Family family = familyRepository.findByFamilyCode(familyCode).orElse(null);

        if (family == null) {
            return false;
        }

        member.setFamily(family);
        memberRepository.save(member);

        return true;
    }

    /**
     * 새 가족을 만들고 해당 회원을 해당 가족에 추가. 가족의 유형별 총합을 0으로 초기화.
     * @param member
     */
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

        for (Member member : members) {
            Result result = member.getResult();

        }
    }
}
