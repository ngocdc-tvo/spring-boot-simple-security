package com.sample.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sample.spring_security.entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
}
