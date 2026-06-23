package com.vai.unified.auth;

import com.vai.unified.auth.googleAuth.GoogleAuthCreate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UnifiedAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnifiedAuthServiceApplication.class, args);
	}

}
