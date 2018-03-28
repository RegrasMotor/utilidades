package com.prosegur.guvnor.negocio.login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneradorPassword {
	public static void main(String[] args) {

		String password = "prosegur";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		System.out.println(hashedPassword);

	}

}