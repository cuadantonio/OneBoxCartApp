package com.cuadantonio.OneBoxCartApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OneBoxCartAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneBoxCartAppApplication.class, args);
	}

}
