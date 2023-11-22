package com.neighborhood.domain.login.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class RefreshToken {
    @Id
    private String memberId;
    private String value;
}
