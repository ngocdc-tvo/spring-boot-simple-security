package com.sample.spring.controller;

import com.sample.spring.dto.res.UserResDto;
import com.sample.spring.entities.UserEntity;
import com.sample.spring.service.JwtService;
import com.sample.spring.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class UserController {

	private final JwtService jwtService;

	private final UserService userService;

	public UserController(JwtService jwtService, UserService userService) {
		this.jwtService = jwtService;
		this.userService = userService;
	}

	/* ---------------- GET ALL USER ------------------------ */
	@GetMapping(value = "/users")
	public ResponseEntity<List<UserResDto>> getAllUser() {
		ModelMapper m = new ModelMapper();
		return new ResponseEntity<>(
				userService.findAll().stream().map(u -> m.map(u, UserResDto.class)).collect(Collectors.toList()), HttpStatus.OK);
	}

	/* ---------------- GET USER BY ID ------------------------ */
	@GetMapping(value = "/users/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable int id) {
		ModelMapper m = new ModelMapper();
		UserEntity user = userService.findById(id);
		if (user != null) {
			return new ResponseEntity<>(m.map(user, UserResDto.class), HttpStatus.OK);
		}
		return new ResponseEntity<>("Not Found UserEntity", HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<String> login(HttpServletRequest request, @RequestBody UserEntity user) {
		String result;
		HttpStatus httpStatus;
		try {
			if (userService.checkLogin(user)) {
				result = jwtService.generateTokenLogin(user.getUsername());
				httpStatus = HttpStatus.OK;
			} else {
				result = "Wrong userId and password";
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception ex) {
			result = "Server Error";
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(result, httpStatus);
	}

}
