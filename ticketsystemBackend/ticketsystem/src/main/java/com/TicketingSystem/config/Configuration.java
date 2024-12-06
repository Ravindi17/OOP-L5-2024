/*package com.TicketingSystem.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maximumTicketCapacity;

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

    public static final String configFilePath = "ticketConfig.json"; // JSON file path

    // Getter and setter methods with validation
    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets() {
        while (true) {
            System.out.println("Enter Total Tickets (positive values):");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input > 0) {
                    this.totalTickets = input;
                    logger.info("Total Tickets set to: " + totalTickets);
                    break;
                } else {
                    logger.warn("Enter a positive value. Try again.");
                }
            } else {
                logger.warn("Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate() {
        while (true) {
            System.out.println("Enter Ticket Release Rate (positive values):");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input > 0) {
                    ticketReleaseRate = input;
                    break;
                } else {
                    logger.warn("Enter a positive value. Try again.");
                }
            } else {
                logger.warn("Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate() {
        while (true) {
            System.out.println("Enter Customer Retrieval Rate (positive values):");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input > 0) {
                    customerRetrievalRate = input;
                    break;
                } else {
                    logger.warn("Enter a positive value. Try again.");
                }
            } else {
                logger.warn("Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    public int getMaximumTicketCapacity() {
        return maximumTicketCapacity;
    }

    public void setMaximumTicketCapacity() {
        while (true) {
            System.out.println("Enter Maximum Ticket Capacity (positive values):");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input > 0) {
                    maximumTicketCapacity = input;
                    break;
                } else {
                    logger.warn("Enter a positive value. Try again.");
                }
            } else {
                logger.warn("Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    // Save configuration to JSON file
    public static void saveConfiguration(Configuration config) {
        ObjectMapper objectMapper = new ObjectMapper();
        File configFile = new File(configFilePath);

        try {
            // Write the configuration object to the JSON file
            objectMapper.writeValue(configFile, config);
            logger.info("Configuration saved successfully to " + configFilePath);
        } catch (IOException e) {
            logger.warn("Error saving configuration to file: " + e.getMessage());
        }
    }

    public static Configuration loadConfiguration(String configFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        File configFile = new File(configFilePath);

        try {
            if (configFile.exists() && configFile.length() > 0) {
                Configuration config = objectMapper.readValue(configFile, Configuration.class);
                logger.info("Configuration loaded: " + config.toString());  // Log loaded config
                return config;
            } else {
                logger.warn("Config file is empty or does not exist.");
                return null;
            }
        } catch (IOException e) {
            logger.warn("Error loading configuration from file: " + e.getMessage());
            return null;
        }
    }


    public static Configuration promptConfiguration() {
        Scanner scanner = new Scanner(System.in);
        Configuration config = loadConfiguration(configFilePath); // Try to load the configuration

        if(config == null || config.getTotalTickets() == 0) {
            // No valid config file or incomplete configuration, prompt user to enter new configuration
            System.out.println("Valid configurations are not available.");
            config = new Configuration();
            config.setTotalTickets();  // Ask user for input
            config.setTicketReleaseRate();  // Ask user for input
            config.setCustomerRetrievalRate();  // Ask user for input
            config.setMaximumTicketCapacity();  // Ask user for input
            saveConfiguration(config);  // Save to JSON
        }
        else {
            // A valid configuration exists, prompt user to use it or enter new values
            System.out.println("Valid configurations are available. Do you want to use it? (Yes == 1 , No == 2)");
            String option = scanner.next();
            if(option.equals("2")) {
                // User wants to enter new values, so prompt again
                config.setTotalTickets();  // Ask user for new input
                config.setTicketReleaseRate();  // Ask user for new input
                config.setCustomerRetrievalRate();  // Ask user for new input
                config.setMaximumTicketCapacity();  // Ask user for new input
                saveConfiguration(config);  // Save new configuration
            } else {
                System.out.println("Using the loaded configuration.");
            }
        }
        return config;
    }


    // Validate if the loaded configuration has valid values
    private static boolean isValidConfiguration(Configuration config) {
        return config.getTotalTickets() > 0 &&
                config.getTicketReleaseRate() > 0 &&
                config.getCustomerRetrievalRate() > 0 &&
                config.getMaximumTicketCapacity() > 0;
    }
}*/

package com.TicketingSystem.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maximumTicketCapacity;
    private int numVendors;
    private int numCustomers;

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

    public static final String configFilePath = "ticketConfig.json"; // JSON file path

    // Constructor for creating a new Configuration object
    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maximumTicketCapacity, int numVendors, int numCustomers) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maximumTicketCapacity = maximumTicketCapacity;
        this.numVendors = numVendors;
        this.numCustomers = numCustomers;
    }

    // Default constructor for Jackson deserialization
    public Configuration() {
    }

    // Getter methods
    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaximumTicketCapacity() {
        return maximumTicketCapacity;
    }

    public int getNumVendors() {
        return numVendors;
    }

    public int getNumCustomers() {
        return numCustomers;
    }

    // Save configuration to JSON file
    public static void saveConfiguration(Configuration config) {
        ObjectMapper objectMapper = new ObjectMapper();
        File configFile = new File(configFilePath);

        try {
            // Write the configuration object to the JSON file
            objectMapper.writeValue(configFile, config);
            logger.info("Configuration saved successfully to " + configFilePath);
        } catch (IOException e) {
            logger.warn("Error saving configuration to file: " + e.getMessage());
        }
    }

    // Load configuration from JSON file
    public static Configuration loadConfiguration(String configFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        File configFile = new File(configFilePath);

        try {
            if (configFile.exists() && configFile.length() > 0) {
                return objectMapper.readValue(configFile, Configuration.class);
            } else {
                return null; // No valid config found
            }
        } catch (IOException e) {
            logger.warn("Error loading configuration from file: " + e.getMessage());
            return null;
        }
    }

    // Prompt user for configuration inputs
    public static Configuration promptConfiguration() {
        Configuration config = loadConfiguration(configFilePath); // Try to load the configuration

        if (config == null) {
            // No valid config file or empty configuration, prompt user to enter new configuration
            logger.info("No valid configuration found. Prompting user to enter new values.");
            return createNewConfiguration();
        }

        // If a valid configuration exists, prompt user to use it or create a new one
        System.out.println("Loaded configuration: ");
        System.out.println("Total Tickets: " + config.getTotalTickets());
        System.out.println("Ticket Release Rate: " + config.getTicketReleaseRate());
        System.out.println("Customer Retrieval Rate: " + config.getCustomerRetrievalRate());
        System.out.println("Maximum Ticket Capacity: " + config.getMaximumTicketCapacity());
        System.out.println("Number of customers:" + config.getNumCustomers());
        System.out.println("Number of vendors: " + config.getNumVendors());

        System.out.println("Do you want to use this configuration? (Yes == 1 , No == 2)");
        String option = scanner.next();
        if (option.equals("2")) {
            // User wants to enter new values
            return createNewConfiguration();
        } else {
            // Use existing configuration
            logger.info("Using the loaded configuration.");
            return config;
        }
    }

    // Helper method to create a new configuration from user input
    private static Configuration createNewConfiguration() {
        int totalTickets = getUserInput("Enter Total Tickets (positive values):");
        int ticketReleaseRate = getUserInput("Enter Ticket Release Rate (positive values):");
        int customerRetrievalRate = getUserInput("Enter Customer Retrieval Rate (positive values):");
        int maximumTicketCapacity = getUserInput("Enter Maximum Ticket Capacity (positive values):");
        int numCustomers = getUserInput("Enter the number of customers: ");
        int numVendors = getUserInput("Enter the number of vendors: ");

        Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maximumTicketCapacity , numVendors , numCustomers);
        saveConfiguration(config);  // Save the new configuration to the file
        return config;
    }

    // Helper method to safely get user input for integer values
    private static int getUserInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input > 0) {
                    return input;
                } else {
                    logger.warn("Enter a positive value. Try again.");
                }
            } else {
                logger.warn("Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }
    }
}





