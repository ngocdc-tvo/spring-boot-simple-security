package com.sample.spring.controller;

import com.sample.spring.dto.res.TokenResDto;
import com.sample.spring.dto.res.UserResDto;
import com.sample.spring.entity.UserEntity;
import com.sample.spring.security.JwtService;
import com.sample.spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	private final JwtService jwtService;

	private final UserService userService;

	public UserController(JwtService jwtService, UserService userService) {
		this.jwtService = jwtService;
		this.userService = userService;
	}

	@GetMapping(value = "/list")
	public ResponseEntity<List<UserResDto>> getAllUser() {
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResDto> getUserById(@PathVariable int id) {
		return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<TokenResDto> login(HttpServletRequest request, @RequestBody UserEntity user) {
		LocalDateTime now = LocalDateTime.now();
		TokenResDto tokenResDto = new TokenResDto();

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

		tokenResDto.setToken(result);
		tokenResDto.setTime(now);
		return new ResponseEntity<>(tokenResDto, httpStatus);
	}

}
