package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestDto;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class FilterDao {
	
	 @Value("${spring.datasource.url}")
		String queryUrl;
		
		@Value("${spring.datasource.password}")
		String password;
		
		@Value("${spring.datasource.username}")
		String username;
		
		@Value("${spring.datasource.driver-class-name}")
		String driverClassName;
  
	 public ResultSet getRooms(RequestDto request) 
		{
			Connection connection;
			Statement statement;
			ResultSet result = null;
			try 
			{
				Class.forName(driverClassName);
				connection = DriverManager.getConnection(queryUrl, username, password);
				String query = "EXEC findbyroomnumber @c='"+request.getRoomno()+"'";
				log.info(query);
				statement = connection.createStatement();
				result = statement.executeQuery(query);
				return result;
				
			}catch (ClassNotFoundException e1){
				log.info("e1"+e1.getMessage());
				return result;
			} 
			catch (SQLException e2) 
			{
				log.info("e2"+e2.getMessage());
				return result;
			}
			catch(Exception e) {
				log.info("e"+e.getMessage());
				return result;
			}
		}
}

