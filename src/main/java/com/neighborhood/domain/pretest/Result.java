package com.neighborhood.domain.pretest;

import com.neighborhood.domain.users.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Getter
@NoArgsConstructor
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    private String userType;
    @OneToOne
    @JoinColumn(name = "userId")
    private Users users;

    @ElementCollection
    Map<String, Integer> typeScores;

    public void calculateScores(Map<String, Integer> typeScores) {
        this.typeScores = typeScores;
    }

    public void updateType(String userType) {
        this.userType = userType;
    }

    public static Result createResult(Users users) {
        Result result = new Result();
        result.users = users;
        users.addResult(result);

        return result;
    }
}
