/*package com.TicketingSystem.config;

import com.TicketingSystem.model.Configuration;
import com.TicketingSystem.service.ConfigurationService;
import com.TicketingSystem.service.TicketService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConfigurationCLI {

    private final ConfigurationService configurationService;
    private final TicketService ticketService;

    public ConfigurationCLI(ConfigurationService configurationService, TicketService ticketService) {
        this.configurationService = configurationService;
        this.ticketService = ticketService;
    }

    public void startCLI() {
        Scanner scanner = new Scanner(System.in);

        // Load or prompt for configuration
        Configuration config = configurationService.getConfiguration().orElseGet(() -> {
            System.out.println("No existing configuration found. Please enter new values.");
            return promptConfiguration();
        });

        System.out.println("Loaded Configuration: " + config);

        // Prompt to start the ticket system
        System.out.println("Do you want to start the ticket system? (yes/no)");
        String start = scanner.nextLine();

        if ("yes".equalsIgnoreCase(start)) {
            // Pass configuration parameters to startTicketSystem
            ticketService.startTicketSystem(
                    config.getMaximumTicketCapacity(),
                    config.getNumVendors(),
                    config.getNumCustomers(),
                    config.getTicketReleaseRate(),
                    config.getCustomerRetrievalRate(),
                    config.getTotalTickets()


            );
        } else {
            System.out.println("Exiting...");
        }
    }

    private Configuration promptConfiguration() {
        Scanner scanner = new Scanner(System.in);

        promptUserInput(scanner,"Enter Maximum Ticket Capacity: ");
        int maxCapacity = scanner.nextInt();

        promptUserInput(scanner,"Enter Number of Vendors: ");
        int numVendors = scanner.nextInt();

        promptUserInput(scanner,"Enter Number of Customers: ");
        int numCustomers = scanner.nextInt();

        promptUserInput(scanner,"Enter Ticket Release Rate (ms): ");
        int releaseRate = scanner.nextInt();

        promptUserInput(scanner,"Enter Customer Retrieval Rate (ms): ");
        int retrievalRate = scanner.nextInt();

        promptUserInput(scanner,"Enter total number of tickets");
        int totalTickets = scanner.nextInt();

        Configuration config = new Configuration(maxCapacity, numVendors, numCustomers, releaseRate, retrievalRate,totalTickets);
        return configurationService.saveConfiguration(config);
    }
    private int promptUserInput(Scanner scanner, String promptMessage) {
        int value;
        while (true) {
            System.out.print(promptMessage);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Invalid input. Only positive values are allowed. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
    }
}*/
/*package com.TicketingSystem.config;

import com.TicketingSystem.model.Configuration;
import com.TicketingSystem.service.ConfigurationService;
import com.TicketingSystem.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class ConfigurationCLI {

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private TicketService ticketService;

    public void startCLI() {
        Scanner scanner = new Scanner(System.in);
        Optional<Configuration> existingConfig = configurationService.getConfiguration();

        if (existingConfig.isPresent()) {
            System.out.println("Existing Configuration:");
            System.out.println(existingConfig.get());
            System.out.println("Do you want to use the existing configuration? (yes/no)");
            String useExisting = scanner.nextLine();
            if ("yes".equalsIgnoreCase(useExisting)) {
                startTicketSystem(existingConfig.get());
                return;
            }
        }

        System.out.println("Enter new configuration values:");
        Configuration newConfig = new Configuration();

        newConfig.setMaximumTicketCapacity(getValidIntegerInput(scanner, "Enter Maximum Ticket Capacity: "));
        newConfig.setNumVendors(getValidIntegerInput(scanner, "Enter Number of Vendors: "));
        newConfig.setNumCustomers(getValidIntegerInput(scanner, "Enter Number of Customers: "));
        newConfig.setTicketReleaseRate(getValidIntegerInput(scanner, "Enter Ticket Release Rate (ms): "));
        newConfig.setCustomerRetrievalRate(getValidIntegerInput(scanner, "Enter Customer Retrieval Rate (ms): "));
        newConfig.setTotalTickets(getValidIntegerInput(scanner, "Enter Total Number of Tickets: "));

        configurationService.saveConfiguration(newConfig);
        startTicketSystem(newConfig);
    }

    private int getValidIntegerInput(Scanner scanner, String prompt) {
        int value = -1;
        while (value <= 0) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value <= 0) {
                    System.out.println("Value must be a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive integer.");
            }
        }
        return value;
    }

    private void startTicketSystem(Configuration config) {
        ticketService.startTicketSystem(
                config.getMaximumTicketCapacity(),
                config.getNumVendors(),
                config.getNumCustomers(),
                config.getTicketReleaseRate(),
                config.getCustomerRetrievalRate(),
                config.getTotalTickets()
        );
        System.out.println("Ticket system started using CLI mode.");
    }
}
*/
package com.TicketingSystem.config;

import com.TicketingSystem.model.Configuration;
import com.TicketingSystem.service.ConfigurationService;
import com.TicketingSystem.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class ConfigurationCLI {

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private TicketService ticketService;

    public void startCLI() {
        Scanner scanner = new Scanner(System.in);
        Optional<Configuration> existingConfig = configurationService.getConfiguration();

        if (existingConfig.isPresent()) {
            System.out.println("Existing Configuration:");
            System.out.println(existingConfig.get());
            System.out.println("Do you want to use the existing configuration? (yes/no)");
            String useExisting = scanner.nextLine();
            if ("yes".equalsIgnoreCase(useExisting)) {
                startTicketSystem(existingConfig.get());
                return;
            }
        }

        System.out.println("Enter new configuration values:");
        Configuration newConfig = new Configuration();

        newConfig.setMaximumTicketCapacity(getValidIntegerInput(scanner, "Enter Maximum Ticket Capacity: "));
        newConfig.setNumVendors(getValidIntegerInput(scanner, "Enter Number of Vendors: "));
        newConfig.setNumCustomers(getValidIntegerInput(scanner, "Enter Number of Customers: "));
        newConfig.setTicketReleaseRate(getValidIntegerInput(scanner, "Enter Ticket Release Rate (ms): "));
        newConfig.setCustomerRetrievalRate(getValidIntegerInput(scanner, "Enter Customer Retrieval Rate (ms): "));
        newConfig.setTotalTickets(getValidIntegerInput(scanner, "Enter Total Number of Tickets: "));

        configurationService.saveConfiguration(newConfig);
        startTicketSystem(newConfig);
    }

    private int getValidIntegerInput(Scanner scanner, String prompt) {
        int value = -1;
        while (value <= 0) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value <= 0) {
                    System.out.println("Value must be a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive integer.");
            }
        }
        return value;
    }

    private void startTicketSystem(Configuration config) {
        ticketService.startTicketSystem(
                config.getMaximumTicketCapacity(),
                config.getNumVendors(),
                config.getNumCustomers(),
                config.getTicketReleaseRate(),
                config.getCustomerRetrievalRate(),
                config.getTotalTickets()
        );
        System.out.println("Ticket system started using CLI mode.");
    }
}

