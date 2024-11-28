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

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

    public static final String configFilePath = "ticketConfig.json"; // JSON file path


    // Getter and setter methods with validation
    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets() {
        while (true) {
            System.out.println("Enter Total Tickets (positive values)");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input > 0) {
                    this.totalTickets = input;
                    logger.info("Total Tickets set to:" + totalTickets);
                    break;
                } else {
                    logger.warn("Enter a positive value. Try again");
                }
            } else {
                logger.warn("Please enter a valid integer");
                scanner.next(); // Clear invalid input
            }
        }
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate() {
        while (true) {
            System.out.println("Enter Ticket Release Rate (positive values)");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input > 0) {
                    ticketReleaseRate = input;
                    break;
                } else {
                    logger.warn("Enter a positive value. Try again");
                }
            } else {
                logger.warn("Please enter a valid integer");
                scanner.next(); // Clear invalid input
            }
        }
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate() {
        while (true) {
            System.out.println("Enter Customer Retrieval Rate (positive values)");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input > 0) {
                    customerRetrievalRate = input;
                    break;
                } else {
                    System.out.println("Enter a positive value. Try again");
                }
            } else {
                System.out.println("Please enter a valid integer");
                scanner.next(); // Clear invalid input
            }
        }
    }

    public int getMaximumTicketCapacity() {
        return maximumTicketCapacity;
    }

    public void setMaximumTicketCapacity() {
        while (true) {
            System.out.println("Enter Maximum Ticket Capacity (positive values)");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input > 0) {
                    maximumTicketCapacity = input;
                    break;
                } else {
                    System.out.println("Enter a positive value. Try again");
                }
            } else {
                System.out.println("Please enter a valid integer");
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
        try{
            File configFile = new File(configFilePath);
            if(!configFile.exists()){
                logger.info("Configuration file does not exist");
                return new Configuration();
            }
            return objectMapper.readValue(configFile, Configuration.class);
        }catch (IOException e){
            logger.error("Failed to load configuration: " + e.getMessage());
            return null;
        }
    }
}
