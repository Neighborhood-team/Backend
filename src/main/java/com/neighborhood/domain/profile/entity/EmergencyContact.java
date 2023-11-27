package com.neighborhood.domain.profile.entity;

import com.neighborhood.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class EmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EmergencyContactId;

    @Column
    private String name;

    @Column
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static EmergencyContact createEmergencyContact() {
        return new EmergencyContact();
    }

    public void setEmergencyContact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
