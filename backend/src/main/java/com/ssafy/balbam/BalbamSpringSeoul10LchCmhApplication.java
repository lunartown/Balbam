package com.ssafy.balbam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ssafy"})
public class BalbamSpringSeoul10LchCmhApplication {

	public static void main(String[] args) {
		SpringApplication.run(BalbamSpringSeoul10LchCmhApplication.class, args);
	}

}
