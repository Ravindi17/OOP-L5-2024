
package com.TicketingSystem;

import com.TicketingSystem.config.Configuration;
import com.TicketingSystem.systemusers.Customer;
import com.TicketingSystem.systemusers.Vendor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.*;

@SpringBootApplication
public class TicketSystemApplication {

	public static void main(String[] args) {
		// Initialize Spring Application context
		ConfigurableApplicationContext context = SpringApplication.run(TicketSystemApplication.class, args);

		// Prompt user for configuration
		Configuration config = Configuration.promptConfiguration();// Use promptConfiguration to load/get user inputs

		System.out.println("Configuration Loaded: " + config);

		// Save configuration to a JSON file (if needed)
		Configuration.saveConfiguration(config);

		int numCustomers = config.getNumCustomers();
		int numVendors = config.getNumVendors();

		// Initialize the ticket pool with maximum capacity
		TicketPool ticketPool = new TicketPool(config.getMaximumTicketCapacity());

		// ExecutorService to manage threads
		ExecutorService executorService = Executors.newCachedThreadPool();

		List<Vendor> vendors = new ArrayList<>();
		for (int i = 1; i <= numVendors; i++) {
			String vendorID = "Vendor" + String.format("%02d", i);
			Vendor vendor = new Vendor(vendorID , config.getTicketReleaseRate(), ticketPool);
			vendors.add(vendor);
			executorService.submit(vendor);
		}

		List<Customer> customers = new ArrayList<>();
		for (int i = 1; i <= numCustomers; i++) {
			String customerID = "Customer" + String.format("%02d", i);
			Customer customer = new Customer(customerID , config.getTicketReleaseRate(), ticketPool);
			customers.add(customer);
			executorService.submit(customer);
		}

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try{
				customers.forEach(Customer:: stopRunning);
				vendors.forEach(Vendor:: stopRunning);

				executorService.shutdown();

				if(!executorService.awaitTermination(60, TimeUnit.SECONDS)){
					System.err.println("Timed out");
					executorService.shutdownNow();
				}
				System.out.println("Ticket pool terminated");
			}catch (InterruptedException e){
				System.err.println("error during shutdown");
				Thread.currentThread().interrupt();
			}
		}));
	}
}
