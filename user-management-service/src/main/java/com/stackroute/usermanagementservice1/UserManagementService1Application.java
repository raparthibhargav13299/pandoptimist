package com.stackroute.usermanagementservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UserManagementService1Application {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementService1Application.class, args);
	}

}
