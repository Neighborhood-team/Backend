package com.neighborhood.domain.profile.repository;

import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.profile.entity.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {

}
