package com.jchcranelist;

import com.jchcranelist.utils.AdminProperities;
import com.jchcranelist.model.User;
import com.jchcranelist.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JchCraneListApplication implements CommandLineRunner {

	private final UserService userService;



	private final AdminProperities adminProperities;
	public JchCraneListApplication(UserService userService, AdminProperities adminProperities) {
		this.userService = userService;
		this.adminProperities = adminProperities;
	}

	public static void main(String[] args) {
		SpringApplication.run(JchCraneListApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User admin = new User();
		admin.setPassword(adminProperities.getPassword());
		admin.setEmail(adminProperities.getEmail());
		admin.setRole(adminProperities.getRole());
		admin.setSurname(adminProperities.getSurname());
		admin.setName(adminProperities.getName());
		admin.setPhoneNumber(adminProperities.getPhone());
		userService.saveUser(admin);
	}
}
