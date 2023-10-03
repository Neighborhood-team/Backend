package com.neighborhood.domain.pretest.service;

import com.neighborhood.domain.pretest.Result;
import com.neighborhood.domain.pretest.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ResultDeleteService {
    private final ResultRepository resultRepository;

    public Result findResult(Long resultId) {
        return resultRepository.findById(resultId)
                .orElseThrow(() -> new IllegalArgumentException("해당 결과가 없습니다. RESULT_ID=" + resultId));
    }

    @Transactional
    public Long delete(Long resultId) {
        Result result = findResult(resultId);
        resultRepository.delete(result);

        return resultId;
    }
}
