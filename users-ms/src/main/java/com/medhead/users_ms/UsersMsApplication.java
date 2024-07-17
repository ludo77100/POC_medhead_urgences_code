package com.medhead.users_ms;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersMsApplication {

	private static final Logger logger = LoggerFactory.getLogger(UsersMsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UsersMsApplication.class, args);
		logger.info("Micro Service UserMsApplication Started");
	}

}
