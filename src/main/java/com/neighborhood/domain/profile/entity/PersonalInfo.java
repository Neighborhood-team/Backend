package com.neighborhood.domain.profile.entity;

import com.neighborhood.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Getter
@NoArgsConstructor
public class PersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personalInfoId;

    @Column
    private String mbti;

    @Column
    private String bloodType;

    @Column
    private String likes;

    @Column
    private String hates;

    @Column
    private String interests;

    @Column
    private String motto;

    @Column
    private String roleInFam;

    @Column
    private String placeToTrip;

    @Column
    private String birthdayGift;

    @Column
    private String mySizes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static PersonalInfo createPersonalInfo() {
        return new PersonalInfo();
    }

    public void updatePersonalInfo(String mbti, String bloodType, String likes, String hates, String interests, String motto, String roleInFam, String placeToTrip, String birthdayGift, String mySizes) {
        this.mbti = mbti;
        this.bloodType = bloodType;
        this.likes = likes;
        this.hates = hates;
        this.interests = interests;
        this.motto = motto;
        this.roleInFam = roleInFam;
        this.placeToTrip = placeToTrip;
        this.birthdayGift = birthdayGift;
        this.mySizes = mySizes;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
