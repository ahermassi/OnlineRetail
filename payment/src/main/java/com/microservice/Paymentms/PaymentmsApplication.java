package com.microservice.Paymentms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentmsApplication.class, args);
	}

}
