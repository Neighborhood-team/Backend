package com.neighborhood.domain.member.service;

import com.neighborhood.domain.family.entity.Family;
import com.neighborhood.domain.family.repository.FamilyRepository;
import com.neighborhood.domain.login.jwt.TokenProvider;
import com.neighborhood.domain.login.service.LoginService;
import com.neighborhood.domain.member.dto.MemberCheckDuplicateParentsDto;
import com.neighborhood.domain.member.dto.MemberResponseDto;
import com.neighborhood.domain.member.dto.MemberUpdateRequestDto;
import com.neighborhood.domain.member.entity.FamilyRole;
import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import com.neighborhood.domain.profile.entity.PersonalInfo;
import com.neighborhood.domain.profile.repository.PersonalInfoRepository;
import com.neighborhood.global.exception.RestApiException;
import com.neighborhood.global.exception.errorCode.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
public class MemberManageService {
    private final MemberRepository memberRepository;
    private final FamilyRepository familyRepository;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final PersonalInfoRepository personalInfoRepository;
    private final LoginService loginService;
    private final TokenProvider tokenProvider;

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
    public Member save(String phone,String fcmTocken) {
        Member member = Member.createMember();
        member.setMemberPhone(phone);
        member.setFcmToken(fcmTocken);
        PersonalInfo personalInfo = PersonalInfo.createPersonalInfo();

        memberRepository.save(member);
        personalInfoRepository.save(personalInfo);
        personalInfo.setMember(member);

        return member;
    }

    @Transactional
    public MemberResponseDto update(Long memberId, MemberUpdateRequestDto requestDto) {
        Member member = findMember(memberId);
        Family family = findFamily(member.getFamily().getFamilyCode());

        List<Member> members = family.getMembers();
        for(Member m : members) {
            if(m.getFamilyRole() == null) break;
            if(requestDto.getFamilyRole().equals(FamilyRole.DAD) && m.getFamilyRole().equals(FamilyRole.DAD)) {
                throw new RestApiException(MemberErrorCode.DAD_ALREADY_EXISTS);
            }
            else if(requestDto.getFamilyRole().equals(FamilyRole.MOM) && m.getFamilyRole().equals(FamilyRole.MOM)) {
                throw new RestApiException(MemberErrorCode.MOM_ALREADY_EXISTS);
            }
        }

        member.updateMemberInfo(requestDto.getName(), requestDto.getFamilyRole(), LocalDate.parse(requestDto.getBirthDate(), dateTimeFormatter));

        return new MemberResponseDto(member);
    }

    @Transactional
    public Long delete(Long memberId) {
        Member member = findMember(memberId);
        String foundMemberId = String.valueOf(memberId);
        loginService.logout(foundMemberId);
        memberRepository.delete(member);

        return memberId;
    }

    @Transactional
    public MemberResponseDto findFirstMemberInFamily(String familyCode) {
        Family family = findFamily(familyCode);
        Member member = memberRepository.findFirstByFamilyOrderByCreatedDateAsc(family);

        return new MemberResponseDto(member);
    }

    public Boolean checkDuplicateParents(MemberCheckDuplicateParentsDto memberCheckDuplicateParentsDto) {
        Member member = findMember(memberCheckDuplicateParentsDto.getMemberId());
        Family family = findFamily(member.getFamily().getFamilyCode());
        List<Member> members = family.getMembers();
        for (Member m : members) {
            if (memberCheckDuplicateParentsDto.getFamilyRole().equals(FamilyRole.DAD) && m.getFamilyRole().equals(FamilyRole.DAD)) {
                return false;
            }
            else if (memberCheckDuplicateParentsDto.getFamilyRole().equals(FamilyRole.MOM) && m.getFamilyRole().equals(FamilyRole.MOM)) {
                return false;
            }
        }
        return true;
    }

    @Transactional
    public MemberResponseDto getMemberInfo(String token) {
        Member member = findMember(Long.parseLong(tokenProvider.getMemberId(token)));

        return new MemberResponseDto(member);
    }


}
