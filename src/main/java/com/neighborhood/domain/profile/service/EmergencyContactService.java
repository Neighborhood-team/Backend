package com.neighborhood.domain.profile.service;

import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import com.neighborhood.domain.profile.dto.EmergencyContactInputDto;
import com.neighborhood.domain.profile.dto.EmergencyContactResponseDto;
import com.neighborhood.domain.profile.entity.EmergencyContact;
import com.neighborhood.domain.profile.repository.EmergencyContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmergencyContactService {
    private final EmergencyContactRepository emergencyContactRepository;
    private final MemberRepository memberRepository;

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
    }

    public EmergencyContact findEmergencyContact(Long emergencyContactId) {
        return emergencyContactRepository.findById(emergencyContactId)
                .orElseThrow(() -> new IllegalArgumentException("해당 비상 연락망이 없습니다."));
    }

    @Transactional
    public EmergencyContactResponseDto save(Long memberId, EmergencyContactInputDto emergencyContactInputDto) {
        Member member = findMember(memberId);
        EmergencyContact emergencyContact = EmergencyContact.createEmergencyContact();

        emergencyContact.setEmergencyContact(emergencyContactInputDto.getName(), emergencyContactInputDto.getPhone());
        emergencyContactRepository.save(emergencyContact);
        emergencyContact.setMember(member);

        return new EmergencyContactResponseDto(emergencyContact);
    }

    @Transactional
    public EmergencyContactResponseDto update(Long emergencyContactId, EmergencyContactInputDto emergencyContactInputDto) {
        EmergencyContact emergencyContact = findEmergencyContact(emergencyContactId);
        emergencyContact.setEmergencyContact(emergencyContactInputDto.getName(), emergencyContactInputDto.getPhone());

        return new EmergencyContactResponseDto(emergencyContact);
    }

    @Transactional
    public List<EmergencyContactResponseDto> getAllEmergencyContact(Long memberId) {
        Member member = findMember(memberId);
        List<EmergencyContactResponseDto> emergencyContactResponseDtos = new ArrayList<>();
        List<EmergencyContact> emergencyContacts = member.getEmergencyContacts();

        for (EmergencyContact e : emergencyContacts) {
            emergencyContactResponseDtos.add(new EmergencyContactResponseDto(e));
        }

        return emergencyContactResponseDtos;
    }
}