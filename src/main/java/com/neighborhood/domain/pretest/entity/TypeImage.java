package com.neighborhood.domain.pretest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class TypeImage {
    @Id
    @GeneratedValue
    private Long imageId;

    @Column
    private String imageName;

    @Column
    private String imageUrl;

    @OneToOne
    @JoinColumn(name = "resultId")
    private Result result;

    public static TypeImage createTypeImage() {
        TypeImage typeImage = new TypeImage();
        return typeImage;
    }
}
