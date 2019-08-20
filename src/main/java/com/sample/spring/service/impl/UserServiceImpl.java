package com.sample.spring.service.impl;

import com.sample.spring.dto.res.UserResDto;
import com.sample.spring.entity.UserEntity;
import com.sample.spring.repository.UserRepo;
import com.sample.spring.service.UserService;
import com.sample.spring.utils.ModelMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepo userRepo;

	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public List<UserResDto> findAll() {
		return ModelMapperUtils.mapAll(userRepo.findAll(), UserResDto.class);
	}

	@Override
	public UserResDto findById(int id) {
		Optional<UserEntity> optionalEntity = userRepo.findById(id);
		return optionalEntity
				.map(registerEntity -> ModelMapperUtils.map(registerEntity, UserResDto.class))
				.orElse(new UserResDto());
	}

	@Override
	public UserEntity loadUserByUsername(String username) {
		List<UserEntity> listUser = userRepo.findAll();

		for (UserEntity user : listUser) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	@Override
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
