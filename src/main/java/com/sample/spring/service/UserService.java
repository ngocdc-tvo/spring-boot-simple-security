package com.sample.spring.service;

import com.sample.spring.dto.res.UserResDto;
import com.sample.spring.entities.UserEntity;

import java.util.List;

public interface UserService {
    List<UserResDto> findAll();

    UserResDto findById(int id);

    UserEntity loadUserByUsername(String username);

    boolean checkLogin(UserEntity user);
}
