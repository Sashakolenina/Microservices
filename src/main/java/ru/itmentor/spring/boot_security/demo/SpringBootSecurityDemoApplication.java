package ru.itmentor.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itmentor.spring.boot_security.demo.service.UserService;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
		UserService userService = context.getBean(UserService.class);
		userService.runMethods();
	}

}
