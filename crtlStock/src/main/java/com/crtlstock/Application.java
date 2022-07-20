package com.crtlstock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.crtlstock")//senza questo error: 404 not found
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
