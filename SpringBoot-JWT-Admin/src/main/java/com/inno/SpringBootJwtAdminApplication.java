package com.inno;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.inno.entity.User;
import com.inno.repository.UserRepository;

@SpringBootApplication
public class SpringBootJwtAdminApplication {
	@Autowired
	private UserRepository userRepo;

	@PostConstruct
	private void initUser() {
		List<User> users = Stream.of(
				new User(101, "admin", "admin@123", "admin@gmail.com"),
				new User(104, "admin1", "admin1@123", "admin1@gmail.com"))
				.collect(Collectors.toList());
		 userRepo.saveAll(users);
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtAdminApplication.class, args);
	}

}
