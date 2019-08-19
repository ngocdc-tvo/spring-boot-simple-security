package com.sample.spring.service;

import com.sample.spring.entities.UserEntity;
import com.sample.spring.repository.UserRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private final UserRepo userRepo;

	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	public List<UserEntity> findAll() {
		List<UserEntity> userEntityList = userRepo.findAll();
		return userEntityList;
	}

	public UserEntity findById(int id) {
		List<UserEntity> listUser = userRepo.findAll();

		for (UserEntity user : listUser) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public UserEntity loadUserByUsername(String username) {
		List<UserEntity> listUser = userRepo.findAll();

		for (UserEntity user : listUser) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	public boolean checkLogin(UserEntity user) {
		List<UserEntity> listUser = userRepo.findAll();

		for (UserEntity userExist : listUser) {
			if (StringUtils.equals(user.getUsername(), userExist.getUsername())
					&& StringUtils.equals(user.getPassword(), userExist.getPassword())) {
				return true;
			}
		}
		return false;
	}
}
