
package com.TicketingSystem;

import com.TicketingSystem.config.Configuration;
import com.TicketingSystem.systemusers.Customer;
import com.TicketingSystem.systemusers.Vendor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import static com.TicketingSystem.config.Configuration.*;

@SpringBootApplication
public class TicketSystemApplication {


	public static void main(String[] args) {
		SpringApplication.run(TicketSystemApplication.class, args);

		TicketPool ticketPool = new TicketPool(20); // Assuming TicketPool has a default constructor

		Customer customer1 = new Customer("C01", 5, ticketPool);
		Customer customer2 = new Customer("C02", 2, ticketPool );

		Thread customerthread1 = new Thread(customer1);
		Thread customerthread2 = new Thread(customer2);

		customerthread1.start();
		customerthread2.start();

		Vendor vendor1 = new Vendor("V1", 5, 2, ticketPool);
		Vendor vendor2 = new Vendor("V2", 10, 3, ticketPool) ;

		Thread vendorThread1 = new Thread(vendor1);
		Thread vendorThread2 = new Thread(vendor2);

		vendorThread1.start();
		vendorThread2.start();

		Configuration config = loadConfiguration("ticketConfig.json");

		config.setTotalTickets();
		config.setTicketReleaseRate();
		config.setCustomerRetrievalRate();
		config.setMaximumTicketCapacity();



		// Save the updated configuration to JSON file
		saveConfiguration(config );

	}
}
