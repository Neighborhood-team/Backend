package com.neighborhood.domain.pretest.service;

import com.neighborhood.domain.pretest.entity.Result;
import com.neighborhood.domain.pretest.entity.ResultManager;
import com.neighborhood.domain.pretest.dto.ResultResponseDto;
import com.neighborhood.domain.pretest.dto.ResultSaveRequestDto;
import com.neighborhood.domain.pretest.entity.TypeImage;
import com.neighborhood.domain.pretest.repository.ResultRepository;
import com.neighborhood.domain.pretest.repository.TypeImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Log4j2
public class ResultManageService {
    private final ResultRepository resultRepository;
    private final TypeImageRepository typeImageRepository;

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
        result.updateResultCode(ResultManager.generateResultCode(6));

        resultRepository.save(result);

        TypeImage typeImage = findTypeImage(ResultManager.matchTypeImage(ResultManager.getType(result.getTypeScores())));
        result.addTypeImage(typeImage);
        result.setTypeNumber(ResultManager.matchTypeImage(ResultManager.getType(result.getTypeScores()))-1);

        return new ResultResponseDto(result);
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
