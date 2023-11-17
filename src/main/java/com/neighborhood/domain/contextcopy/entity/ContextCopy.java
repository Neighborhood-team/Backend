package com.neighborhood.domain.contextcopy.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "ContextCopy")
@Getter
public class ContextCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long context_id;

    //문장 내용
    private String context;

    //문장 타이틀
    private String context_title;

    //자식 or 부모
    private String context_role;


}
