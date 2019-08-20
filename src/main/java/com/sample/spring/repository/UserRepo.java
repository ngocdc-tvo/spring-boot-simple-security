package com.sample.spring.repository;

import com.sample.spring.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Integer>, UserRepoCustom {
}
