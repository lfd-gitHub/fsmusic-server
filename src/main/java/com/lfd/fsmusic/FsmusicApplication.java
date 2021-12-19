package com.lfd.fsmusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FsmusicApplication {

	public static void main(String[] args) {
		System.out.println("DBNAME:" + System.getenv("DATABASE_NAME"));;
		SpringApplication.run(FsmusicApplication.class, args);
	}

}
