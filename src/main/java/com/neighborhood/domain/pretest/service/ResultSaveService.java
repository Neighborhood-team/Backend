package com.neighborhood.domain.pretest.service;

import com.neighborhood.domain.pretest.Result;
import com.neighborhood.domain.pretest.ScoreCalculator;
import com.neighborhood.domain.pretest.dto.ResultSaveRequestDto;
import com.neighborhood.domain.pretest.repository.ResultRepository;
import com.neighborhood.domain.users.Users;
import com.neighborhood.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ResultSaveService {
    private final ResultRepository resultRepository;
    private final UsersRepository userRepository;

    public Result findResult(Long resultId) {
        return resultRepository.findById(resultId)
                .orElseThrow(() -> new IllegalArgumentException("해당 결과가 없습니다. RESULT_ID=" + resultId));
    }

    public Users findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. USER_ID=" + userId));
    }

    @Transactional
    public Long save(Long userId, ResultSaveRequestDto requestDto) {
        Users users = findUser(userId);
        Result result = Result.createResult(users);

        result.calculateScores(ScoreCalculator.getScoreMap(requestDto.getScores()));
        result.updateType(ScoreCalculator.getType(result.getTypeScores()));

        return resultRepository.save(result).getResultId();
    }
}
