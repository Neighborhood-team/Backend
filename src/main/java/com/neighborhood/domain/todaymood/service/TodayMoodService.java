package com.neighborhood.domain.todaymood.service;

import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import com.neighborhood.domain.todaymood.dto.TodayMoodListDto;
import com.neighborhood.domain.todaymood.dto.TodayMoodUpdateDto;
import com.neighborhood.domain.todaymood.entity.TodayMood;
import com.neighborhood.domain.todaymood.repository.TodayMoodRepository;
import com.neighborhood.global.exception.RestApiException;
import com.neighborhood.global.exception.errorCode.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class TodayMoodService {

    private final TodayMoodRepository todayMoodRepository;
    private final MemberRepository memberRepository;

    public Long saveTodayMood(TodayMoodUpdateDto todayMoodDto){

        Member member = memberRepository.findById(todayMoodDto.getMood_id()).orElseThrow();
        TodayMood todayMood = TodayMood.builder().mood(todayMoodDto.getMood())
                                .member(member).message(todayMoodDto.getMessage()).build();
        todayMoodRepository.save(todayMood);

        return todayMood.getTodayMood_id();
    }

    @Scheduled(cron = "0 0 18 * * *")
    public void deleteScheduler(){
        todayMoodRepository.deleteAll();
    }

    //조회기능 null값 예외처리해줘야함 + 생년월일 기준 sort해서 보내줘야함
    public List<TodayMoodListDto> getFamilyMoodList(Principal principal) {
        Long memberId = Long.parseLong(principal.getName());
        Member member = memberRepository.findById(memberId).orElseThrow();
        List<Member> members = memberRepository.findByFamily_FamilyId(member.getFamily().getFamilyId());
        if(members==null||member==null){
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
        List<TodayMoodListDto> resDtos = new ArrayList<>();

        for (int i = 0; i < members.size(); i++) {
            TodayMood familyMood = todayMoodRepository.findByMember_MemberId(members.get(i).getMemberId());
            if (familyMood != null) {
                TodayMoodListDto todayMoodListDto = TodayMoodListDto.builder().memberId(members.get(i).getMemberId()).memberName(members.get(i).getName()).birthDate(String.valueOf(members.get(i).getBirthDate()))
                        .memberRole(members.get(i).getFamilyRole().toString()).moodId(familyMood.getTodayMood_id()).message(familyMood.getMessage())
                        .mood(familyMood.getMood()).build();
                resDtos.add(todayMoodListDto);
            } else {
                TodayMoodListDto todayMoodListDto = TodayMoodListDto.builder().memberId(members.get(i).getMemberId()).memberName(members.get(i).getName()).birthDate(String.valueOf(members.get(i).getBirthDate()))
                        .memberRole(members.get(i).getFamilyRole().toString()).moodId(null).message(null).mood(null).build();
                resDtos.add(todayMoodListDto);
            }
            }
            Collections.sort(resDtos, (mood1, mood2) -> mood1.getBirthDate().compareTo(mood2.getBirthDate()));
            //생년월일 sort하면 됌
                return resDtos;
        }


    //수정기능 id는 path변수로 받기(todaymood)+위 로직 수정해서 id값도 같이 보내게 수정하기
    public Long updateTodayMood(TodayMoodUpdateDto todayMoodUpdateDto){
        TodayMood todayMood = todayMoodRepository.findById(todayMoodUpdateDto.getMood_id()).orElseThrow();
        todayMoodUpdateDto.updateToDto(todayMood,todayMoodUpdateDto);

        return todayMood.getTodayMood_id();
    }
}
