package com.neighborhood.domain.member.entity;

import com.neighborhood.domain.family.entity.Family;
import com.neighborhood.domain.pretest.entity.Result;
import com.neighborhood.domain.todayquestion.entity.TodayQuestionAnswer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column
    private String name;

    @Column(unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    private FamilyRole familyRole;

    @Column
    private LocalDate birthDate;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    private Family family;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Result result;

    @OneToMany(mappedBy = "member")
    private List<TodayQuestionAnswer> todayQuestionAnswers = new ArrayList<>();

    public static Member createMember() {
        Member member = new Member();
        return member;
    }

    public void updateMemberInfo(String name, FamilyRole familyRole, LocalDate birthDate) {
        this.name = name;
        this.familyRole = familyRole;
        this.birthDate = birthDate;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public void setMemberPhone(String phone) {
        this.phone = phone;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    @Override
    public String getUsername() {
        return String.valueOf(memberId);
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }
}
