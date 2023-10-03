package com.neighborhood.domain.member.service;

import com.neighborhood.domain.member.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberDeleteService {
    private final MemberRepository memberRepository;

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. MEMBER_ID=" + memberId));
    }

    @Transactional
    public Long delete(Long memberId) {
        Member member = findMember(memberId);
        memberRepository.delete(member);

        return memberId;
    }
}
