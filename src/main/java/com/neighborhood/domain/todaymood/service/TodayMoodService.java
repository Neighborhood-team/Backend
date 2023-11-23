package com.neighborhood.domain.todaymood.service;

import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import com.neighborhood.domain.todaymood.dto.TodayMoodDto;
import com.neighborhood.domain.todaymood.dto.TodayMoodListDto;
import com.neighborhood.domain.todaymood.dto.TodayMoodUpdateDto;
import com.neighborhood.domain.todaymood.entity.TodayMood;
import com.neighborhood.domain.todaymood.repository.TodayMoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodayMoodService {

    private final TodayMoodRepository todayMoodRepository;
    private final MemberRepository memberRepository;

    public Long saveTodayMood(TodayMoodDto todayMoodDto){

        Member member = memberRepository.findById(todayMoodDto.getMemberId()).orElseThrow();
        TodayMood todayMood = TodayMood.builder().mood(todayMoodDto.getMood())
                                .member(member).message(todayMoodDto.getMessage()).build();
        todayMoodRepository.save(todayMood);

        return todayMood.getTodayMood_id();
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void deleteScheduler(){
        todayMoodRepository.deleteAll();
    }

    //조회기능 null값 예외처리해줘야함 + 생년월일 기준 sort해서 보내줘야함
    public List<?> getFamilyMoodList(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        List<Member> members = memberRepository.findByFamily_FamilyId(member.getFamily().getFamilyId());
        List<TodayMoodListDto> resDtos = new ArrayList<>();

        for (int i = 0; i < members.size(); i++) {
            TodayMood familyMood = todayMoodRepository.findByMember_MemberId(members.get(i).getMemberId());
            if (familyMood != null) {
                TodayMoodListDto todayMoodListDto = TodayMoodListDto.builder().memberName(members.get(i).getName())
                        .memberRole(members.get(i).getFamilyRole().toString()).moodId(familyMood.getTodayMood_id()).message(familyMood.getMessage()).mood(familyMood.getMood()).build();
                resDtos.add(todayMoodListDto);
            } else {
                TodayMoodListDto todayMoodListDto = TodayMoodListDto.builder().memberName(members.get(i).getName())
                        .memberRole(members.get(i).getFamilyRole().toString()).moodId(null).message(null).mood(null).build();
                resDtos.add(todayMoodListDto);
            }
            }
            //생년월일 sort하면 됌
                return resDtos;
        }


    //수정기능 id는 path변수로 받기(todaymood)+위 로직 수정해서 id값도 같이 보내게 수정하기
    public Long updateTodayMood(TodayMoodUpdateDto todayMoodUpdateDto, Long id){
        TodayMood todayMood = todayMoodRepository.findById(id).orElseThrow();
        todayMoodUpdateDto.updateToDto(todayMood,todayMoodUpdateDto);

        return todayMood.getTodayMood_id();
    }
}
