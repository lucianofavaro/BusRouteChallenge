package com.goeuro.busrouting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.goeuro.busrouting")
public class BusroutingApplication {
	public static void main(String[] args) {
		SpringApplication.run(BusroutingApplication.class, args);
	}
}
