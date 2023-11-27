package com.neighborhood.domain.profile.controller;

import com.neighborhood.domain.profile.dto.EmergencyContactInputDto;
import com.neighborhood.domain.profile.dto.EmergencyContactResponseDto;
import com.neighborhood.domain.profile.service.EmergencyContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("emergency")
public class EmergencyContactController implements EmergencyContactApi {
    private final EmergencyContactService emergencyContactService;

    @PostMapping("/{memberId}")
    public EmergencyContactResponseDto save(@PathVariable Long memberId, @RequestBody EmergencyContactInputDto emergencyContactInputDto) {
        return emergencyContactService.save(memberId, emergencyContactInputDto);
    }

    @PutMapping("/{emergencyContactId}")
    public EmergencyContactResponseDto update(@PathVariable Long emergencyContactId , @RequestBody EmergencyContactInputDto emergencyContactInputDto) {
        return emergencyContactService.update(emergencyContactId, emergencyContactInputDto);
    }

    @GetMapping("/{memberId}")
    public List<EmergencyContactResponseDto> getAllEmergencyContacts(@PathVariable Long memberId) {
        return emergencyContactService.getAllEmergencyContact(memberId);
    }
}
