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
        List<ScheduleDto.InquiryForm> dtos = new ArrayList<>();

        for (Schedule s : schedules) {
            Boolean isOngoing = !LocalDate.now().isBefore(s.getStartDate()) && !LocalDate.now().isAfter(s.getEndDate());
            dtos.add(new ScheduleDto.InquiryForm(s.getScheduleId(), String.valueOf(s.getStartDate()), String.valueOf(s.getEndDate()), s.getContent(), isOngoing));
        }

        return new ScheduleDto.ScheduleList(dtos);
    }
}
