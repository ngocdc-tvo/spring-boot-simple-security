package com.sample.spring.controller;

import com.sample.spring.dto.res.AddressResDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

	@GetMapping("/list")
	public ResponseEntity<List<AddressResDto>> getAddressList() {
		List<AddressResDto> addressResDtoList = new ArrayList<>();
		addressResDtoList.add(new AddressResDto("1", "Da Nang"));
		addressResDtoList.add(new AddressResDto("2", "Ha Noi"));

		return new ResponseEntity<>(addressResDtoList, HttpStatus.OK);
	}

}
