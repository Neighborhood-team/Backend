package com.neighborhood.domain.context_copy.entity;


import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ContextCopy")
@Getter
public class ContextCopy {

    @Id
    private Long context_id;

    //문장 내용
    private String context;

    //문장 타이틀
    private String context_title;

    //자식 or 부모
//    private enum context_type


}
