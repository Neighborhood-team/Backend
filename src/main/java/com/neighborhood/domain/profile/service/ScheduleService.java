package com.neighborhood.domain.profile.service;

import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.profile.dto.ScheduleDto;
import com.neighborhood.domain.profile.entity.Schedule;
import com.neighborhood.domain.profile.repository.SchduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {

    private final SchduleRepository schduleRepository;

    @Transactional
    public void addSchedule(Member member, ScheduleDto.InputForm form) {

        String target = form.getTarget();

        if(target.equals("FAMILY")){
            List<Member> members = member.getFamily().getMembers();
            for (Member m : members) {
                Schedule schedule = new Schedule(form.getStartDate(), form.getEndDate(), form.getContent(), m);
                schduleRepository.save(schedule);
                m.getSchedules().add(schedule);
            }
            return ;
        }

        Schedule schedule = new Schedule(form.getStartDate(), form.getEndDate(), form.getContent(), member);
        schduleRepository.save(schedule);
        member.getSchedules().add(schedule);

    }


    @Transactional
    public ScheduleDto.ScheduleList getSchedules(Member member) {

        List<Schedule> schedules = schduleRepository.findByMemberOrderByStartDateAsc(member);

        List<ScheduleDto.InquiryForm> completeDtos = new ArrayList<>();
        List<ScheduleDto.InquiryForm> ongoingDtos = new ArrayList<>();
        List<ScheduleDto.InquiryForm> plannedDtos = new ArrayList<>();
        List<ScheduleDto.InquiryForm> resultDtos = new ArrayList<>();

        for (Schedule s : schedules) {
            if(s.getEndDate().isBefore(LocalDate.now())){
                completeDtos.add(new ScheduleDto.InquiryForm(s.getScheduleId(), String.valueOf(s.getStartDate()), String.valueOf(s.getEndDate()), s.getContent(), false));
            } else if (!LocalDate.now().isBefore(s.getStartDate()) && !LocalDate.now().isAfter(s.getEndDate())){
                ongoingDtos.add(new ScheduleDto.InquiryForm(s.getScheduleId(), String.valueOf(s.getStartDate()), String.valueOf(s.getEndDate()), s.getContent(), true));
            } else {
                plannedDtos.add(new ScheduleDto.InquiryForm(s.getScheduleId(), String.valueOf(s.getStartDate()), String.valueOf(s.getEndDate()), s.getContent(), false));
            }
        }

        resultDtos.addAll(completeDtos);
        resultDtos.addAll(ongoingDtos);
        resultDtos.addAll(plannedDtos);

        return new ScheduleDto.ScheduleList(resultDtos);
    }
}
