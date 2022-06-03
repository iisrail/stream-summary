package com.contin.assg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ContinApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContinApplication.class, args);
	}

}
