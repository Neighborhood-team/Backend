package com.neighborhood.domain.profile.service;

import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import com.neighborhood.domain.profile.dto.PersonalInfoInputDto;
import com.neighborhood.domain.profile.dto.PersonalInfoResponseDto;
import com.neighborhood.domain.profile.entity.PersonalInfo;
import com.neighborhood.domain.profile.repository.PersonalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonalInfoService {
    private final PersonalInfoRepository personalInfoRepository;
    private final MemberRepository memberRepository;


    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
    }

    public PersonalInfo findPersonalInfo(Long personalInfoId) {
        return personalInfoRepository.findById(personalInfoId)
                .orElse(null);
    }

    @Transactional
    public PersonalInfoResponseDto updatePersonalInfo(Long memberId, PersonalInfoInputDto personalInfoInputDto) {
        Member member = findMember(memberId);
        Long foundPersonalInfoId = member.getPersonalInfo().getPersonalInfoId();

        PersonalInfo personalInfo = findPersonalInfo(foundPersonalInfoId);
        personalInfo.updatePersonalInfo(personalInfoInputDto.getMbti(), personalInfoInputDto.getBloodType(), personalInfoInputDto.getLikes(),
                personalInfoInputDto.getHates(), personalInfoInputDto.getInterests(), personalInfoInputDto.getMotto(),
                personalInfoInputDto.getRoleInFam(), personalInfoInputDto.getPlaceToTrip(), personalInfoInputDto.getBirthdayGift(),
                personalInfoInputDto.getMySizes());

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
