package com.TicketingSystem.service;

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
    public void startTicketSystem(int maxCapacity, int numVendors, int numCustomers, int releaseRate, int retrievalRate , int totaltickets) {
        // Initialize the TicketPool
        this.ticketPool = new TicketPool(maxCapacity,totaltickets);

        List<Thread> threads = new ArrayList<>();

        // Create and start vendor threads
        for (int i = 1; i <= numVendors; i++) {
            Vendor vendor = new Vendor("Vendor-" + i, releaseRate, ticketPool);
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
    public void addTicket() throws InterruptedException {
        ticketPool.addTicket();
        System.out.println("A ticket has been added manually.");
    }

    /**
     * Removes a ticket from the ticket pool and returns its ID.
     *
     * @return The ID of the removed ticket.
     * @throws InterruptedException If the thread is interrupted while removing a ticket.
     */
    public int removeTicket() throws InterruptedException {
        int ticketId = ticketPool.removeTicket();
        System.out.println("A ticket has been removed manually. Ticket ID: " + ticketId);
        return ticketId;
    }

    /**
     * Returns the current status of the ticket pool.
     *
     * @return A string describing the current status of the ticket pool.
     */
    public String getTicketPoolStatus() {
        return ticketPool.getStatus();
    }
}
