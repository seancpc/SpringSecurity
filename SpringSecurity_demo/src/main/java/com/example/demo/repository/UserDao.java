package com.example.demo.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	public Map<String, Map<String, String>> users;
	{
		// A01, 1234, admin,normal,ROLE_manager
		Map<String, String> info1 = new LinkedHashMap<>();
		info1.put("password", "$2a$10$I2ysl8gELP2ogm8l/s/OP.oiZiSElQ57rhoVi8tsmeTHOeLu6apQ.");
		info1.put("authority", "admin,normal,ROLE_manager");
		
		// A02, 5678, normal,ROLE_employee
		Map<String, String> info2 = new LinkedHashMap<>();
		info2.put("password", "$2a$10$QCr6HkstBXijBUbSPzqvuOLvugGhq4SKxzKC2PTp4rfYYt79ryWUK");
		info2.put("authority", "normal,ROLE_employee");
		
		users = new LinkedHashMap<>();
		users.put("A01", info1);
		users.put("A02", info2);
		
		System.out.println(users);
	}
}