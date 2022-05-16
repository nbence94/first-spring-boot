package com.example.firstmyown;

import com.example.firstmyown.repository.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstMyOwnApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstMyOwnApplication.class, args);
	}

}
