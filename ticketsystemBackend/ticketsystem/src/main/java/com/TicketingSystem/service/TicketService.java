package com.TicketingSystem.service;

import com.TicketingSystem.model.Customer;
import com.TicketingSystem.model.TicketPool;
import com.TicketingSystem.model.Vendor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*package com.TicketingSystem.service;

import com.TicketingSystem.model.Customer;
import com.TicketingSystem.model.TicketPool;
import com.TicketingSystem.model.Vendor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private TicketPool ticketPool;

    public TicketService() {
        // Default initialization (you can change this if you dynamically configure TicketPool later)
        this.ticketPool = new TicketPool(10,20); // Default max capacity is 10
    }

    /**
     * Initializes and starts the ticket system with the given configuration parameters.
     *
     * @param maxCapacity  Maximum capacity of the ticket pool.
     * @param numVendors   Number of vendors.
     * @param numCustomers Number of customers.
     * @param releaseRate  Ticket release rate in milliseconds.
     * @param retrievalRate Customer retrieval rate in milliseconds.
     * @param totaltickets total number of ticktes in the pool at a given time
     */
    /*public void startTicketSystem(int maxCapacity, int numVendors, int numCustomers, int releaseRate, int retrievalRate , int totaltickets) {
        // Initialize the TicketPool
        this.ticketPool = new TicketPool(maxCapacity,totaltickets);

        List<Thread> threads = new ArrayList<>();

        // Create and start vendor threads
        for (int i = 1; i <= numVendors; i++) {
            Vendor vendor = new Vendor(numVendors + i, releaseRate, ticketPool);
            Thread vendorThread = new Thread(() -> {
                try {
                    vendor.produceTickets();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads.add(vendorThread);
            vendorThread.start();
        }

        // Create and start customer threads
        for (int i = 1; i <= numCustomers; i++) {
            Customer customer = new Customer("Customer-" + i, retrievalRate, ticketPool);
            Thread customerThread = new Thread(() -> {
                try {
                    customer.consumeTickets(ticketPool);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads.add(customerThread);
            customerThread.start();
        }

        System.out.println("Ticket system started with vendors and customers.");
    }

    /**
     * Adds a ticket to the ticket pool.
     *
     * @throws InterruptedException If the thread is interrupted while adding a ticket.
     */
    /*public void addTicket() throws InterruptedException {
        ticketPool.addTicket();
        System.out.println("A ticket has been added manually.");
    }

    /**
     * Removes a ticket from the ticket pool and returns its ID.
     *
     * @return The ID of the removed ticket.
     * @throws InterruptedException If the thread is interrupted while removing a ticket.
     */
    /*public int removeTicket() throws InterruptedException {
        int ticketId = ticketPool.removeTicket();
        System.out.println("A ticket has been removed manually. Ticket ID: " + ticketId);
        return ticketId;
    }

    /**
     * Returns the current status of the ticket pool.
     *
     * @return A string describing the current status of the ticket pool.
     */
   /* public String getTicketPoolStatus() {
        return ticketPool.getStatus();
    }


}*/
@Service
public class TicketService {

    private volatile boolean isRunning = false; // Flag to track system state
    private int totalEntered = 0;
    private int totalSold = 0;
    private int totalLeft = 0;
    private TicketPool ticketPool;

    public void startTicketSystem(int maxCapacity, int numVendors, int numCustomers, int releaseRate, int retrievalRate , int totaltickets) {
        // Initialize the TicketPool
        this.ticketPool = new TicketPool(maxCapacity,totaltickets);

        List<Thread> threads = new ArrayList<>();

        // Create and start vendor threads
        for (int i = 1; i <= numVendors; i++) {
            Vendor vendor = new Vendor( i, releaseRate, ticketPool);
            Thread vendorThread = new Thread(() -> {
                try {
                    vendor.produceTickets();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads.add(vendorThread);
            vendorThread.start();
        }

        // Create and start customer threads
        for (int i = 1; i <= numCustomers; i++) {
            Customer customer = new Customer("Customer-" + i, retrievalRate, ticketPool);
            Thread customerThread = new Thread(() -> {
                try {
                    customer.consumeTickets(ticketPool);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads.add(customerThread);
            customerThread.start();
        }

        System.out.println("Ticket system started with vendors and customers.");
    }


    // Start the ticket system
    public void startSystem() {
        isRunning = true;
        totalLeft = totalEntered; // Reset totalLeft when starting
        new Thread(this::simulateVendors).start();
        new Thread(this::simulateCustomers).start();
    }

    // Stop the ticket system
    public void stopSystem() {
        isRunning = false; // Set the flag to stop the threads
    }

    // Simulate vendors adding tickets to the pool
    private void simulateVendors() {
        while (isRunning) {
            try {
                Thread.sleep(1000); // Vendor adds tickets every second
                synchronized (this) {
                    if (totalLeft < totalEntered) {
                        totalLeft++;
                        System.out.println("Vendor added a ticket. Total left: " + totalLeft);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor thread interrupted.");
            }
        }
        System.out.println("Vendors stopped.");
    }

    // Simulate customers retrieving tickets from the pool
    private void simulateCustomers() {
        while (isRunning) {
            try {
                Thread.sleep(1500); // Customer retrieves tickets every 1.5 seconds
                synchronized (this) {
                    if (totalLeft > 0) {
                        totalLeft--;
                        totalSold++;
                        System.out.println("Customer retrieved a ticket. Total left: " + totalLeft);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer thread interrupted.");
            }
        }
        System.out.println("Customers stopped.");
    }

    // Get the summary of tickets
    public Map<String, Integer> getSummary() {
        Map<String, Integer> summary = new HashMap<>();
        summary.put("totalEntered", totalEntered);
        summary.put("totalSold", totalSold);
        summary.put("totalLeft", totalLeft);
        return summary;
    }

    // Save the initial configuration (max tickets, etc.)
    public void saveConfiguration(int totalTickets) {
        totalEntered = totalTickets;
        totalLeft = totalTickets;
        totalSold = 0;
    }
}
