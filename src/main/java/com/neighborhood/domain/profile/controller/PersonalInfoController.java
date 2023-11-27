package com.neighborhood.domain.profile.controller;

import com.neighborhood.domain.profile.dto.PersonalInfoInputDto;
import com.neighborhood.domain.profile.dto.PersonalInfoResponseDto;
import com.neighborhood.domain.profile.service.PersonalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("personalInfo")
public class PersonalInfoController implements PersonalInfoApi {
    private final PersonalInfoService personalInfoService;

    @PutMapping("/{memberId}")
    public PersonalInfoResponseDto updatePersonalInfo(@PathVariable Long memberId, @RequestBody PersonalInfoInputDto personalInfoInputDto) {
        return personalInfoService.updatePersonalInfo(memberId, personalInfoInputDto);
    }

    @GetMapping("/{memberId}")
    public PersonalInfoResponseDto getPersonalInfo(@PathVariable Long memberId) {
        return personalInfoService.getPersonalInfo(memberId);
    }
}
