package com.neighborhood.domain.profile.service;

import com.neighborhood.domain.family.entity.Family;
import com.neighborhood.domain.family.repository.FamilyRepository;
import com.neighborhood.domain.login.jwt.TokenProvider;
import com.neighborhood.domain.member.entity.FamilyRole;
import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import com.neighborhood.domain.profile.dto.PersonalInfoInputDto;
import com.neighborhood.domain.profile.dto.PersonalInfoResponseDto;
import com.neighborhood.domain.profile.entity.PersonalInfo;
import com.neighborhood.domain.profile.repository.PersonalInfoRepository;
import com.neighborhood.global.exception.RestApiException;
import com.neighborhood.global.exception.errorCode.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalInfoService {
    private final PersonalInfoRepository personalInfoRepository;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final FamilyRepository familyRepository;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
    }

    public PersonalInfo findPersonalInfo(Long personalInfoId) {
        return personalInfoRepository.findById(personalInfoId)
                .orElse(null);
    }

    public Family findFamily(String familyCode) {
        return familyRepository.findByFamilyCode(familyCode)
                .orElseThrow(() -> new IllegalArgumentException("가족 코드를 재확인하세요!"));
    }

    @Transactional
    public PersonalInfoResponseDto updatePersonalInfo(Long memberId, PersonalInfoInputDto personalInfoInputDto) {
        Member member = findMember(memberId);
        Long foundPersonalInfoId = member.getPersonalInfo().getPersonalInfoId();

        Family family = findFamily(member.getFamily().getFamilyCode());


        PersonalInfo personalInfo = findPersonalInfo(foundPersonalInfoId);
        personalInfo.updatePersonalInfo(personalInfoInputDto.getMbti(), personalInfoInputDto.getBloodType(), personalInfoInputDto.getLikes(),
                personalInfoInputDto.getHates(), personalInfoInputDto.getInterests(), personalInfoInputDto.getMotto(),
                personalInfoInputDto.getRoleInFam(), personalInfoInputDto.getPlaceToTrip(), personalInfoInputDto.getBirthdayGift(),
                personalInfoInputDto.getMySizes());

        List<Member> members = family.getMembers();
        for(Member m : members) {
            if(m.getFamilyRole() == null) break;
            if(personalInfoInputDto.getFamilyRole().equals(FamilyRole.DAD) && m.getFamilyRole().equals(FamilyRole.DAD)) {
                throw new RestApiException(MemberErrorCode.DAD_ALREADY_EXISTS);
            }
            else if(personalInfoInputDto.getFamilyRole().equals(FamilyRole.MOM) && m.getFamilyRole().equals(FamilyRole.MOM)) {
                throw new RestApiException(MemberErrorCode.MOM_ALREADY_EXISTS);
            }
        }
        member.updateFixedInfo(personalInfoInputDto.getName(), personalInfoInputDto.getFamilyRole(), LocalDate.parse(personalInfoInputDto.getBirthDate(), dateTimeFormatter));


        return new PersonalInfoResponseDto(personalInfo);
    }

    @Transactional
    public PersonalInfoResponseDto getPersonalInfo(Long memberId) {
        Member member = findMember(memberId);
        Long foundPersonalInfoId = member.getPersonalInfo().getPersonalInfoId();
        PersonalInfo personalInfo = findPersonalInfo(foundPersonalInfoId);

        return new PersonalInfoResponseDto(personalInfo);
    }
}
