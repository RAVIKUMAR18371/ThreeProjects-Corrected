package com.springboot.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/*
 * WHY WE NEED THIS:
 * This is the Service Registry (Eureka Server) for the microservices setup.
 * Both address-service and employee-service register themselves here on startup,
 * so they can discover each other by name instead of hardcoded host:port values.
 *
 * IMPORTANT: @EnableEurekaServer is what actually turns this plain Spring Boot app
 * into a Eureka registry. It was MISSING in the original project, so even though
 * the pom.xml had the eureka-server dependency, this app was not functioning as
 * a discovery server at all.
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
