package com.neighborhood.domain.member.service;

import com.neighborhood.domain.member.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import com.neighborhood.domain.member.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberSaveService {
    private final MemberRepository memberRepository;

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. MEMBER_ID=" + memberId));
    }

    @Transactional
    public Long save(MemberSaveRequestDto requestDto) {
        Member member = requestDto.toEntity();

        return memberRepository.save(member).getMemberId();
    }
}