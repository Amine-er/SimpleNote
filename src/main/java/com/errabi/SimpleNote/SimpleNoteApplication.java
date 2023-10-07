package com.errabi.SimpleNote;

import com.errabi.SimpleNote.web.model.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class SimpleNoteApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(SimpleNoteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// to convert java object to json or json to java object
		ObjectMapper mapper = new ObjectMapper();

		UserDto user =  UserDto.builder()
				.id(1l)
				.email("email.test@gmail.com")
				.password("admin")
				.username("admin")
				.notes(new ArrayList<>())
				.build();

		String userDtoJson = mapper.writeValueAsString(user);

		System.out.println("JSON : "+userDtoJson);
	}
}
