package com.TicketingSystem;

import com.TicketingSystem.config.ConfigurationCLI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.TicketingSystem.repository")
public class TicketSystemApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TicketSystemApplication.class, args);

		// Start the CLI
		ConfigurationCLI cli = context.getBean(ConfigurationCLI.class);
		cli.startCLI();
	}
}

