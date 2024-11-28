
/*package com.TicketingSystem;

import com.TicketingSystem.config.Configuration;
import com.TicketingSystem.systemusers.Customer;
import com.TicketingSystem.systemusers.Vendor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import static com.TicketingSystem.config.Configuration.*;

@SpringBootApplication
public class TicketSystemApplication {
	private Configuration config;

	public  void ticketSystem() {
		TicketPool ticketPool = new TicketPool(config.getMaximumTicketCapacity()); // Assuming TicketPool has a default constructor

		Customer customer1 = new Customer("C01", 5, ticketPool);
		Customer customer2 = new Customer("C02", 2, ticketPool);

		Thread customerthread1 = new Thread(customer1);
		Thread customerthread2 = new Thread(customer2);

		customerthread1.start();
		customerthread2.start();

		Vendor vendor1 = new Vendor("V1", 5, 2, ticketPool);
		Vendor vendor2 = new Vendor("V2", 10, 3, ticketPool);

		Thread vendorThread1 = new Thread(vendor1);
		Thread vendorThread2 = new Thread(vendor2);

		vendorThread1.start();
		vendorThread2.start();

	}
	public static void main(String[] args) {
		SpringApplication.run(TicketSystemApplication.class, args);

		Configuration config = new Configuration();

		config.setTotalTickets();
		config.setTicketReleaseRate();
		config.setCustomerRetrievalRate();
		config.setMaximumTicketCapacity();

		saveConfiguration(config );
	}

}*/

package com.TicketingSystem;

import com.TicketingSystem.config.Configuration;
import com.TicketingSystem.systemusers.Customer;
import com.TicketingSystem.systemusers.Vendor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketSystemApplication.class, args);

		// Prompt user for configuration
		Configuration config = new Configuration();
		config.setTotalTickets();
		config.setTicketReleaseRate();
		config.setCustomerRetrievalRate();
		config.setMaximumTicketCapacity();

		// Save configuration to a JSON file (if needed)
		Configuration.saveConfiguration(config);

		// Initialize the ticket pool with maximum capacity
		TicketPool ticketPool = new TicketPool(config.getMaximumTicketCapacity());

		// Create and start customer threads
		Customer customer1 = new Customer("C01", config.getCustomerRetrievalRate(), ticketPool);
		Customer customer2 = new Customer("C02", config.getCustomerRetrievalRate(), ticketPool);

		Thread customerThread1 = new Thread(customer1);
		Thread customerThread2 = new Thread(customer2);

		customerThread1.start();
		customerThread2.start();

		// Create and start vendor threads
		Vendor vendor1 = new Vendor("V01", config.getTicketReleaseRate(), 5, ticketPool);
		Vendor vendor2 = new Vendor("V02", config.getTicketReleaseRate(), 3, ticketPool);

		Thread vendorThread1 = new Thread(vendor1);
		Thread vendorThread2 = new Thread(vendor2);

		vendorThread1.start();
		vendorThread2.start();

		System.out.println("Ticket system is running. Check the logs for activity.");
	}
}


