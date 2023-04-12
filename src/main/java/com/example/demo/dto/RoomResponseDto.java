package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



@Data
@AllArgsConstructor
public class RoomResponseDto {
	
	String roomno;
	int capacity;
	boolean ac;
	boolean tv;
	boolean board;
}