package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.service.FilterService;
import com.example.demo.dto.*;




@RestController
@CrossOrigin(origins="*")

public class FiltersController {
	
  @Autowired
  private  FilterService service;
  
  @Value("${jwt-validate}")
  String jwtUrl;
  
  
  @PostMapping(path="/findbyroomnumber")
  public ResponseDto getRooms(@RequestBody RequestDto request, @RequestHeader("Authorization") String auth) {
	  ResponseDto response = new ResponseDto();
		try {
			JwtRequestDto jwtRequest = new JwtRequestDto();
			RestTemplate template = new RestTemplate();
			jwtRequest.setToken(auth);
			JwtResponseDto jwtResponse = template.postForObject(jwtUrl, jwtRequest ,JwtResponseDto.class);
			if(jwtResponse.isStatus()) {
				response = this.service.getRooms(request);
			}
			else {
				response.setRoomEntity(null);
				response.setResponseCode("403");
				response.setResponseDescription("Error: Unauthorized");
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.setRoomEntity(null);
			response.setResponseCode("95");
			response.setResponseDescription(e.getMessage());
			
		}
		return response;
	} 
}