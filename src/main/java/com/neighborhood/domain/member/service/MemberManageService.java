package com.neighborhood.domain.member.service;

import com.neighborhood.domain.family.entity.Family;
import com.neighborhood.domain.family.repository.FamilyRepository;
import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class MemberManageService {
    private final MemberRepository memberRepository;
    private final FamilyRepository familyRepository;

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. MEMBER_ID=" + memberId));
    }

    public Family findFamily(String familyCode) {
        return familyRepository.findByFamilyCode(familyCode)
                .orElseThrow(() -> new IllegalArgumentException("가족 코드를 재확인하세요!"));
    }

    @Transactional
    public Long delete(Long memberId) {
        Member member = findMember(memberId);
        memberRepository.delete(member);

        return memberId;
    }

    @Transactional
    public String findFirstMemberInFamily(String familyCode) {
        Family family = findFamily(familyCode);
        Member member = memberRepository.findFirstByFamilyOrderByCreatedDateAsc(family).orElse(null);

        return member.getName();
    }

}
