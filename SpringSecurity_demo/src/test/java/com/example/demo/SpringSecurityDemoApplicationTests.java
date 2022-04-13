package com.example.demo;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


class SpringSecurityDemoApplicationTests {

	
	public static void main(String[] args) {
		
		PasswordEncoder pe = new BCryptPasswordEncoder();
		String ecode = pe.encode("1234");
		System.out.println(ecode);
		boolean matches = pe.matches("1234", ecode);
		System.out.println(matches);
		System.out.println();
		
		String ecode2 = pe.encode("5678");
		System.out.println(ecode2);
		boolean matches2 = pe.matches("5678", ecode);
		System.out.println(matches2);
		
	}

}
