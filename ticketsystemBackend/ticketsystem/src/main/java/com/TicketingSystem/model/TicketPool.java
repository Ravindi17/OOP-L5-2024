package com.TicketingSystem.model;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TicketPool {

    private static final Logger log = LoggerFactory.getLogger(TicketPool.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Setter
    @Getter
    private int maxCapacity;

    @Setter
    @Getter
    private int totalTickets;

    @Setter
    @Getter
    @ElementCollection
    private List<Integer> tickets = new ArrayList<>();

    private int nextTicketId = 1;

    public TicketPool() {
    }

    public TicketPool(int maxCapacity , int totalTickets) {
        this.maxCapacity = maxCapacity;
        this.totalTickets = totalTickets;
    }

    public synchronized void addTicket() throws InterruptedException {
        while (tickets.size() >= maxCapacity) {
            log.warn("TicketPool is full. Waiting to add a ticket...");
            wait(); // Wait if pool is full
        }
        tickets.add(nextTicketId++);
        log.info("Ticket added. Current size: " + tickets.size());
        notifyAll(); // Notify threads waiting to consume tickets
    }

    public synchronized int removeTicket() throws InterruptedException {
        while (tickets.isEmpty()) {
            System.out.println("TicketPool is empty. Waiting to remove a ticket...");
            wait(); // Wait if pool is empty
        }
        int ticket = tickets.remove(0);
        log.info("Ticket removed (ID: " + ticket + "). Current size: " + tickets.size());
        notifyAll(); // Notify threads waiting to produce tickets
        return ticket;
    }

    public synchronized String getStatus() {
        return "Tickets in Pool: " + tickets.size() + " / " + maxCapacity;
    }

    public void resetPool() {
        tickets.clear();
        nextTicketId = 1;
        System.out.println("TicketPool has been reset.");
    }
}
