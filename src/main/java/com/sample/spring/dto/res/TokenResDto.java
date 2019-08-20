package com.sample.spring.dto.res;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TokenResDto {
    String token;
    LocalDateTime time;
}
