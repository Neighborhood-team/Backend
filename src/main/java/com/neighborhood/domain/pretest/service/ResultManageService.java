package com.neighborhood.domain.pretest.service;

import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import com.neighborhood.domain.pretest.dto.ResultResponseMemberDto;
import com.neighborhood.domain.pretest.entity.Result;
import com.neighborhood.domain.pretest.entity.ResultManager;
import com.neighborhood.domain.pretest.dto.ResultResponseDto;
import com.neighborhood.domain.pretest.dto.ResultSaveRequestDto;
import com.neighborhood.domain.pretest.entity.TypeImage;
import com.neighborhood.domain.pretest.repository.ResultRepository;
import com.neighborhood.domain.pretest.repository.TypeImageRepository;
import com.neighborhood.global.dto.MessageOnlyResponseDto;
import com.neighborhood.global.exception.RestApiException;
import com.neighborhood.global.exception.errorCode.CommonErrorCode;
import com.neighborhood.global.util.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@RequiredArgsConstructor
@Service
@Log4j2
public class ResultManageService {
    private final ResultRepository resultRepository;
    private final TypeImageRepository typeImageRepository;
    private final MemberRepository memberRepository;

    public Result findResult(Long resultId) {
        return resultRepository.findById(resultId)
                .orElseThrow(() -> new IllegalArgumentException("해당 결과가 없습니다. RESULT_ID=" + resultId));
    }

    public TypeImage findTypeImage(Long imageId) {
        return typeImageRepository.findById(imageId)
                .orElseThrow(() -> new IllegalArgumentException("해당 이미지가 없습니다. IMAGE_ID=" + imageId));
    }

    @Transactional
    public ResultResponseDto save(ResultSaveRequestDto requestDto) {
        Result result = Result.createResult();

        result.calculateScores(ResultManager.getScoreMap(requestDto.getScores()));
        result.updateTypeAndDate(ResultManager.getType(result.getTypeScores()));
        result.updateResultCode(RandomCodeUtil.generateResultCode(6));

        resultRepository.save(result);

        TypeImage typeImage = findTypeImage(ResultManager.matchTypeImage(ResultManager.getType(result.getTypeScores())));
        result.addTypeImage(typeImage);
        result.setTypeNumber(ResultManager.matchTypeImage(ResultManager.getType(result.getTypeScores()))-1);

        return new ResultResponseDto(result);
    }

    @Transactional
    public ResultResponseMemberDto connectResult(String resultCode, Principal principal) {

        Member member = memberRepository.findById(Long.parseLong(principal.getName()))
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        Result result = resultRepository.findByResultCode(resultCode);
        if(result!=null){
            result.updateMember(member);
            return new ResultResponseMemberDto(result);
        }
        return new ResultResponseMemberDto(null);
    }

    @Transactional
    public Result findResultMember(Principal principal) {
        Result result = resultRepository.findByMember_MemberId(Long.parseLong(principal.getName()));
        if(result!=null){
            return result;
        }
        return null;
    }

    @Transactional
    public Long delete(Long resultId) {
        Result result = findResult(resultId);
        resultRepository.delete(result);

        return resultId;
    }

    @Transactional
    public Long getTestCount() {
        return resultRepository.count();
    }
}
