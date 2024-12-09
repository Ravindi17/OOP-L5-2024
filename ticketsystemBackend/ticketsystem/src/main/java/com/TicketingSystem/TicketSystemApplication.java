/*package com.TicketingSystem;

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

}*/
/*
package com.TicketingSystem;

import com.TicketingSystem.config.ConfigurationCLI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TicketSystemApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Choose mode of operation:");
		System.out.println("1. CLI");
		System.out.println("2. Frontend");
		String choice = scanner.nextLine();

		if ("1".equals(choice)) {
			ConfigurationCLI cli = new ConfigurationCLI();
			cli.startCLI();
		} else if ("2".equals(choice)) {
			SpringApplication.run(TicketSystemApplication.class, args);
		} else {
			System.out.println("Invalid choice. Please restart and select either 1 or 2.");
		}
	}
}
*/

package com.TicketingSystem;

import com.TicketingSystem.config.ConfigurationCLI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class TicketSystemApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Choose mode of operation:");
		System.out.println("1. CLI");
		System.out.println("2. Frontend");
		String choice = scanner.nextLine();

		ApplicationContext context = SpringApplication.run(TicketSystemApplication.class, args);

		if ("1".equals(choice)) {
			ConfigurationCLI cli = context.getBean(ConfigurationCLI.class);
			cli.startCLI();
		} else if ("2".equals(choice)) {
			System.out.println("Starting frontend...");
		} else {
			System.out.println("Invalid choice. Please restart and select either 1 or 2.");
		}
	}
}
