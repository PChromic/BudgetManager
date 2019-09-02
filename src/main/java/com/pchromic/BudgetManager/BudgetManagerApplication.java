package com.pchromic.BudgetManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BudgetManagerApplication {

	public static void main(String[] args) {

/*		// Create a spring context container.
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// Register the spring bean definition java class to load spring beans.
		context.register(AppConfig.class);*/
		SpringApplication.run(BudgetManagerApplication.class, args);
	}

}
