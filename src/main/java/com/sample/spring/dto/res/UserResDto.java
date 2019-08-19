package com.sample.spring.dto.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public class UserResDto {
    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "role")
    private String role;
}
