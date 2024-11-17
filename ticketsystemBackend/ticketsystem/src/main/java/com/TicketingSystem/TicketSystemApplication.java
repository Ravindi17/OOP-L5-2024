/*package com.TicketingSystem;

import com.TicketingSystem.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketSystemApplication {

	private static final Logger logger = LoggerFactory.getLogger(TicketSystemApplication.class);


	public static void main(String[] args) {
		// Start the Spring Boot application
		SpringApplication.run(TicketSystemApplication.class, args);

		// Create an instance of the Configuration class
		Configuration config = new Configuration();

		// File path to the ticketConfig.json
		String configFilePath = "src/main/resources/ticketConfig.json"; // Adjust path if necessary

		try {
			// Load configuration
			config.systemConfiguration(configFilePath);
			System.out.println("Configuration loaded successfully!");

			// Save configuration as an example of calling a non-static method
			config.systemConfiguration(configFilePath);
			System.out.println("Configuration saved successfully!");

			// Print the configuration to verify
			System.out.println("Total Tickets: " + config.getTotalTickets());
			System.out.println("Ticket Release Rate: " + config.getTicketReleaseRate());
			System.out.println("Customer Retrieval Rate: " + config.getCustomerRetrievalRate());
			System.out.println("Max Ticket Capacity: " + config.getMaximumTicketCapacity());
		} catch (Exception e) {
			logger.error("Failed to load or save configuration: " + e.getMessage());
		}
	}
}*/

/*package com.TicketingSystem;

import com.TicketingSystem.config.Configuration;
import com.TicketingSystem.systemusers.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class TicketSystemApplication {

	private static final Logger logger = LoggerFactory.getLogger(TicketSystemApplication.class);

	public static void main(String[] args) {
		// Start the Spring Boot application
		SpringApplication.run(TicketSystemApplication.class, args);
		System.out.println("Current working directory: " + System.getProperty("user.dir"));

		String configFilePath = "config.json";
		// Create an instance of the Configuration class

		Configuration config ;
		File configFile = new File(configFilePath);
		ObjectMapper mapper = new ObjectMapper();

		// File path to the ticketConfig.json
		 // Adjust path if necessary
		if (configFile.exists()) {
			try{
				config = ObjectMapper.readValue(configFile, Configuration.class);
				System.out.println("Configuration loaded successfully from file");
				System.out.println("Total Tickets: " + config.getTotalTickets());
				System.out.println("Ticket Release Rate: " + config.getTicketReleaseRate());
				System.out.println("Customer Retrieval Rate: " + config.getCustomerRetrievalRate());
				System.out.println("Max Ticket Capacity: " + config.getMaximumTicketCapacity());
			}catch (IOException e){
				logger.error("Failed to load or save configuration: " + e.getMessage());
				config = new Configuration();
				config.systemConfiguration();
			}
		}else {
			System.out.println("Configuration file does not exist");
			config = new Configuration();
			config.systemConfiguration();
			config.saveConfiguration("config.json");

			config.saveConfiguration("config.json");
			System.out.println("Configuration saved successfully");

			Ticket ticket = new Ticket("Concert Ticket", "VIP Access for the concert");
			saveTicketToJson(ticket, "src/main/resources/ticketConfig.json");
		}

		public static void saveTicketToJson(Ticket ticket, String filePath){
			ObjectMapper objectMapper = new ObjectMapper();

			try {
				// Write the ticket object to a JSON file
				objectMapper.writeValue(new File(filePath), ticket);
				System.out.println("Ticket data has been saved to " + filePath);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error saving ticket data to JSON file.");
			}
		}
	}*/

package com.TicketingSystem;

import com.TicketingSystem.config.Configuration;
import com.TicketingSystem.systemusers.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

import static com.TicketingSystem.config.Configuration.configFilePath;

@SpringBootApplication
public class TicketSystemApplication {

	private static final Logger logger = LoggerFactory.getLogger(TicketSystemApplication.class);

	public static void main(String[] args) {
		// Start the Spring Boot application
		SpringApplication.run(TicketSystemApplication.class, args);

		// Print the current working directory for troubleshooting path issues
		System.out.println("Current working directory: " + System.getProperty("user.dir"));

		// File path to the config.json
		 // Ensure this path is correct

		// Load or create a new Configuration instance
		Configuration config;
		File configFile = new File(configFilePath);
		ObjectMapper objectMapper = new ObjectMapper();

		if (configFile.exists()) {
			try {
				// Load configuration if file exists
				config = objectMapper.readValue(configFile, Configuration.class);
				System.out.println("Configuration loaded successfully from file:");
				System.out.println("Total Tickets: " + config.getTotalTickets());
				System.out.println("Ticket Release Rate: " + config.getTicketReleaseRate());
				System.out.println("Customer Retrieval Rate: " + config.getCustomerRetrievalRate());
				System.out.println("Max Ticket Capacity: " + config.getMaximumTicketCapacity());
			} catch (IOException e) {
				logger.error("Failed to load configuration from file: " + e.getMessage());
				config = new Configuration();
				config.systemConfiguration(); // Prompt user to input new configuration
			}
		} else {
			// Create new configuration if the file doesn't exist
			System.out.println("Configuration file not found. Creating new configuration.");
			config = new Configuration();
			config.systemConfiguration(); // Prompt user to input new configuration
			config.saveConfiguration(configFilePath); // Save the new configuration to the file
		}

		// Save configuration to ensure persistence
		config.saveConfiguration(configFilePath);
		System.out.println("Configuration saved successfully!");

		// Example: Save ticket data to JSON
		Ticket ticket = new Ticket("Concert Ticket", "VIP Access for the concert");

		// Save ticket to JSON file
		saveTicketToJson(ticket, "ticketConfig.json");
	}

	// Method to save a Ticket object to a JSON file
	public static void saveTicketToJson(Ticket ticket, String filePath) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// Write the ticket object to a JSON file
			objectMapper.writeValue(new File(filePath), ticket);
			System.out.println("Ticket data has been saved to " + filePath);
		} catch (IOException e) {
			logger.error("Error saving ticket data to JSON file: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
