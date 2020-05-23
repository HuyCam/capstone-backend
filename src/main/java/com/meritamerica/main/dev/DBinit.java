package com.meritamerica.main.dev;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.meritamerica.main.repositories.MyUserRepo;
import com.meritamerica.main.security.Users;

@Service
public class DBinit implements CommandLineRunner {
	@Autowired
	MyUserRepo userRepo;
	
	@Override
	public void run(String... args) throws Exception {
		// Create users
		Users huy = new Users("huycam", "123", "USER_PRIVILEGE");
		Users david = new Users("david", "123", "USER_PRIVILEGE");
		Users admin = new Users("admin", "123", "ADMIN_PRIVILEGE");
		
		List<Users> users = Arrays.asList(huy, admin, david);
		
		// Save to db
		this.userRepo.saveAll(users);
	}

}
