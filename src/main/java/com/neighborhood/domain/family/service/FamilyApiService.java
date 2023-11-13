package com.neighborhood.domain.family.service;

import com.neighborhood.domain.family.entity.Family;
import com.neighborhood.domain.family.repository.FamilyRepository;
import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FamilyApiService {

    private final FamilyRepository familyRepository;
    private final MemberRepository memberRepository;

    /**
     * 해당 회원을 이미 존재하는 가족에 추가
     * @param member Member 엔티티
     * @param familyCode 가족 고유 코드
     * @return True: 정상적으로 추가 완료 / False: 가족코드에 해당하는 가족 엔티티 조회 불가
     */
    public Boolean addMemberToExistingFamily(Member member, String familyCode) {

        Family family = familyRepository.findByCode(familyCode).orElse(null);

        if (family == null) {
            return false;
        }

        member.setFamily(family);
        memberRepository.save(member);

        return true;
    }

    /**
     * 새 가족을 만들고 해당 회원을 해당 가족에 추가
     * @param member
     */
    public void addMemberToNewFamily(Member member) {

        Family family = new Family();
        familyRepository.save(family);

        member.setFamily(family);
        memberRepository.save(member);

    }
}
