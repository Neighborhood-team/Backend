package com.neighborhood.domain.member.service;

import com.neighborhood.domain.member.dto.MemberResponseDto;
import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.dto.MemberSaveRequestDto;
import com.neighborhood.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberManageService {
    private final MemberRepository memberRepository;

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. MEMBER_ID=" + memberId));
    }

    @Transactional
    public MemberResponseDto save(MemberSaveRequestDto requestDto) {
        Member member = requestDto.toEntity();
        memberRepository.save(member);
        return new MemberResponseDto(member);
    }

    @Transactional
    public Long delete(Long memberId) {
        Member member = findMember(memberId);
        memberRepository.delete(member);

        return memberId;
    }
}
