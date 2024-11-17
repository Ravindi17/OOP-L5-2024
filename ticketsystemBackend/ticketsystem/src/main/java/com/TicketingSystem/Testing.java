package com.TicketingSystem;

import com.TicketingSystem.systemusers.Customer;
import com.TicketingSystem.systemusers.Vendor;

public class Testing {
    public static void main (String[] args) {
        Vendor vendor1 = new Vendor("01", 10, 2000);
       Customer customer1 = new Customer("01", 3000);

        Thread vendorThread = new Thread(vendor1);
        Thread customerThread = new Thread(customer1);

        vendorThread.start();
        customerThread.start();

        TicketPool ticketPool = new TicketPool(4);

        ticketPool.addTickets(2);
        ticketPool.addTickets(4);

        ticketPool.removeTicket();
        ticketPool.removeTicket();
        ticketPool.removeTicket();
        ticketPool.removeTicket();
        ticketPool.removeTicket();

    }
}
