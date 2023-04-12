package com.example.demo.dto;

import lombok.Data;

@Data
public class JwtResponseDto {

	String responseCode;
	String responseDescription;
	boolean status;
}
