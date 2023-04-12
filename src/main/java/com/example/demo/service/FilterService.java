package com.example.demo.service;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.example.demo.dto.*;

import com.example.demo.dao.FilterDao;

@Service
@Slf4j
public class FilterService {
	
  @Autowired
  private FilterDao dao;

  public ResponseDto getRooms(RequestDto request) {
	  ResponseDto response = new ResponseDto();
	  ResultSet result;
	  List<RoomResponseDto> roomEntities = new ArrayList<>();
	  try {
    	  result = dao.getRooms(request);
    	  while (result.next()) {
				String roomNumber = result.getString("roomno");
				log.info(roomNumber);
				int capacity = result.getInt("capacity");
				log.info(""+capacity);
				boolean ac = result.getBoolean("ac");
				log.info("AC"+ac);
				boolean tv = result.getBoolean("tv");
				log.info("TV"+tv);
				boolean board = result.getBoolean("board");
				log.info("Board"+board);
				RoomResponseDto room = new RoomResponseDto(roomNumber, capacity, ac, tv, board);
				log.info(""+room);
				roomEntities.add(room);
			}
    	  if(!(roomEntities.isEmpty())) {
			  response.setResponseCode("00");
			  response.setResponseDescription("This is the filtered room by the user");
			  response.setRoomEntity(roomEntities);
		  }
		  else {
			  response.setResponseCode("01");
			  response.setResponseDescription("No RoomS Found");
			  response.setRoomEntity(roomEntities);  
		  }
    	  log.info(" "+roomEntities);
    	  log.info("Indise in service to filter data with capacity");
	} catch (Exception e) {
		log.error(e.getMessage());
		response.setResponseCode("99");
		  response.setResponseDescription(e.getMessage());
		  response.setRoomEntity(roomEntities);  
		
	}
	  return response;
  }
  
}
