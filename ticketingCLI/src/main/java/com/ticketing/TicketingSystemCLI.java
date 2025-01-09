package com.ticketing;

import java.util.Scanner;

public class TicketingSystemCLI {

    // System parameters
    private static int totalTickets;
    private static int ticketReleaseRate;
    private static int customerRetrievalRate;
    private static int maxTicketCapacity;

    // Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Real-Time Ticketing System!");

        while (true) {
            System.out.println("\nMenu:\n");
            System.out.println("1. Configure System Parameters");
            System.out.println("2. Load System Parameters");
            System.out.println("3. Start Operations");
            System.out.println("4. Stop Operations");
            System.out.println("5. Exit");
            System.out.println("\n");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline


            System.out.println("\n");
            switch (choice) {
                case 1 -> configureSystemParameters();
                case 2 -> loadConfiguration();
                case 3 -> startOperations();
                case 4 -> stopOperations();
                case 5 -> exitSystem();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // In the main TicketingSystemCLI class
    public static void configureSystemParameters() {
        System.out.println("Configure System Parameters:");
        totalTickets = getPositiveInput("Enter total tickets: ");
        maxTicketCapacity = getPositiveInput("Enter maximum ticket capacity: ");
        ticketReleaseRate = getPositiveInput("Enter ticket release rate (tickets/interval): ");
        customerRetrievalRate = getPositiveInput("Enter customer retrieval rate (tickets/interval): ");

        if (maxTicketCapacity < totalTickets) {
            System.out.println("Error: Maximum ticket capacity cannot be less than total tickets.");
            configureSystemParameters();
        } else {
            Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
            JSONConfigurationManager.saveConfigurationToJSON(config, "config.json");
            System.out.println("Configuration saved!");
        }
    }

    public static void loadConfiguration() {
        Configuration config = JSONConfigurationManager.loadConfigurationFromJSON("config.json");
        if (config != null) {
            totalTickets = config.getTotalTickets();
            ticketReleaseRate = config.getTicketReleaseRate();
            customerRetrievalRate = config.getCustomerRetrievalRate();
            maxTicketCapacity = config.getMaxTicketCapacity();
            System.out.println("Configuration loaded!");
            System.out.println("Total tickets: " + totalTickets);
            System.out.println("Ticket release rate: " + ticketReleaseRate);
            System.out.println("Customer retrieval rate: " + customerRetrievalRate);
            System.out.println("Maximum ticket capacity: " + maxTicketCapacity);

        } else {
            System.out.println("Failed to load configuration.");
        }
    }


    private static int getPositiveInput(String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (value > 0) {
                return value;
            }
            System.out.println("Value must be positive. Please try again.");
        }
    }



    private static void exitSystem() {
        System.out.println("Exiting system. Goodbye!");
        scanner.close();
        System.exit(0);
    }

    private static Thread vendorThread;
    private static Thread customerThread;
    private static TicketPool ticketPool;

    private static void startOperations() {
        if (ticketPool == null) {
            ticketPool = new TicketPool(maxTicketCapacity);
        }

        vendorThread = new Thread(new Vendor(ticketPool, ticketReleaseRate));
        customerThread = new Thread(new Customer(ticketPool, customerRetrievalRate));

        vendorThread.start();
        customerThread.start();

        System.out.println("Operations started.");
    }

    private static void stopOperations() {
        if (vendorThread != null) vendorThread.interrupt();
        if (customerThread != null) customerThread.interrupt();

        System.out.println("Operations stopped.");
    }

}
