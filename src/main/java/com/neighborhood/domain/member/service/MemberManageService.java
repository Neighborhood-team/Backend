package com.neighborhood.domain.member.service;

import com.neighborhood.domain.family.entity.Family;
import com.neighborhood.domain.family.repository.FamilyRepository;
import com.neighborhood.domain.member.dto.MemberResponseDto;
import com.neighborhood.domain.member.dto.MemberUpdateRequestDto;
import com.neighborhood.domain.member.entity.FamilyRole;
import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import com.neighborhood.global.exception.RestApiException;
import com.neighborhood.global.exception.errorCode.LoginErrorCode;
import com.neighborhood.global.exception.errorCode.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MemberManageService {
    private final MemberRepository memberRepository;
    private final FamilyRepository familyRepository;

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. MEMBER_ID=" + memberId));
    }

    public Member findMemberByPhone(String phone) {
        return memberRepository.findByPhone(phone)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
    }

    public Family findFamily(String familyCode) {
        return familyRepository.findByFamilyCode(familyCode)
                .orElseThrow(() -> new IllegalArgumentException("가족 코드를 재확인하세요!"));
    }

    @Transactional
    public Member save(String phone) {
        Member member = Member.createMember();
        member.setMemberPhone(phone);
        memberRepository.save(member);

        return member;
    }

    @Transactional
    public MemberResponseDto update(Long memberId, MemberUpdateRequestDto requestDto) {
        Member member = findMember(memberId);
        member.updateMemberInfo(requestDto.getName(), requestDto.getFamilyRole(), requestDto.getBirthDate());

        if(requestDto.getFamilyRole().equals(FamilyRole.DAD) && memberRepository.existsByFamilyRole(FamilyRole.DAD)) {
            throw new RestApiException(MemberErrorCode.DAD_ALREADY_EXISTS);
        }
        if(requestDto.getFamilyRole().equals(FamilyRole.MOM) && memberRepository.existsByFamilyRole(FamilyRole.MOM)) {
            throw new RestApiException(MemberErrorCode.MOM_ALREADY_EXISTS);
        }

        return new MemberResponseDto(member);
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
        Optional<Member> member = memberRepository.findFirstByFamilyOrderByCreatedDateAsc(family);

        if (member.isPresent()) {
            return member.get().getName();
        }
        else {
            throw new RestApiException(MemberErrorCode.FAMILY_NOT_FOUND);
        }
    }

}
