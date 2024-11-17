/*package com.TicketingSystem.config;

import java.util.*;
import java.io.*;
import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maximumTicketCapacity;

    private final Scanner scanner = new Scanner(System.in);

    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets() {
           while (true) {
               System.out.println("Enter Total Tickets (positive values)");
               if (scanner.hasNextInt()) {
                   int input = scanner.nextInt();
                   if (input > 0) {
                       totalTickets = input;
                       break;
                   } else {
                       System.out.println("Enter a positive value. Try again");
                   }
               }else{
                   System.out.println("Please enter a valid integer");
                   scanner.next();
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
                }else{
                    System.out.println("Enter a positive value. Try again");
                }
            }else {
                System.out.println("Please enter a valid integer");
                scanner.next();
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
                }else{
                    System.out.println("Enter a positive value. Try again");
                }
            }else{
                System.out.println("Please enter a valid integer");
                scanner.next();
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
                }else{
                    System.out.println("Enter a positive value. Try again");
                }
            }else {
                System.out.println("Please enter a valid integer");
                scanner.next();
            }
        }
    }

    public void systemConfiguration(String configFilePath) {
        setTotalTickets();
        setTicketReleaseRate();
        setCustomerRetrievalRate();
        setMaximumTicketCapacity();


        logger.info("System Configuration");
        System.out.println("Total Tickets: " + getTotalTickets());
        System.out.println("Ticket Release Rate: " + getTicketReleaseRate());
        System.out.println("Customer Retrieval Rate: " + getCustomerRetrievalRate());
        System.out.println("Maximum Ticket Capacity: " + getMaximumTicketCapacity());
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

    private final Scanner scanner = new Scanner(System.in);
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
                    totalTickets = input;
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
                    System.out.println("Enter a positive value. Try again");
                }
            } else {
                System.out.println("Please enter a valid integer");
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

    public void systemConfiguration() {
        setTotalTickets();
        setTicketReleaseRate();
        setCustomerRetrievalRate();
        setMaximumTicketCapacity();

        logger.info("System Configuration");
        System.out.println("Total Tickets: " + getTotalTickets());
        System.out.println("Ticket Release Rate: " + getTicketReleaseRate());
        System.out.println("Customer Retrieval Rate: " + getCustomerRetrievalRate());
        System.out.println("Maximum Ticket Capacity: " + getMaximumTicketCapacity());
    }

    // Save configuration to JSON file
    public void saveConfiguration(String configFilePath) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(configFilePath), this);
            logger.info("Configuration saved successfully");
        } catch (IOException e) {
            logger.error("Failed to save configuration: " + e.getMessage());
        }
    }

    public static Configuration loadConfiguration(String configFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readValue(new File(configFilePath) , Configuration.class);
        }catch (IOException e){
            logger.error("Failed to load configuration: " + e.getMessage());
            return null;
        }
    }
}
