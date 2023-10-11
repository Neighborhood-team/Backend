//package com.neighborhood.domain.pretest.service;
//
//import com.neighborhood.domain.pretest.Result;
//import com.neighborhood.domain.pretest.ResultManager;
//import com.neighborhood.domain.pretest.dto.ResultSaveRequestDto;
//import com.neighborhood.domain.pretest.repository.ResultRepository;
//import com.neighborhood.domain.member.entity.Member;
//import com.neighborhood.domain.member.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@RequiredArgsConstructor
//@Service
//public class ResultSaveService {
//    private final ResultRepository resultRepository;
//    private final MemberRepository memberRepository;
//
//    public Result findResult(Long resultId) {
//        return resultRepository.findById(resultId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 결과가 없습니다. RESULT_ID=" + resultId));
//    }
//
//    public Member findMember(Long memberId) {
//        return memberRepository.findById(memberId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. MEMBER_ID=" + memberId));
//    }
//
//    @Transactional
//    public Long save(ResultSaveRequestDto requestDto) {
//        Result result = Result.createResult();
//
//        result.calculateScores(ResultManager.getScoreMap(requestDto.getScores()));
//        result.updateType(ResultManager.getType(result.getTypeScores()));
//
//        return resultRepository.save(result).getResultId();
//    }
//}
