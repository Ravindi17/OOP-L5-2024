package com.ticketing;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<Integer> tickets = new LinkedList<>();
    private final int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTickets(int count) {
        while (tickets.size() + count > maxCapacity) {
            System.out.println("Ticket pool full. Vendor waiting...");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        for (int i = 0; i < count; i++) {
            tickets.add(1); // Add a dummy ticket
        }
        System.out.println(count + " tickets added. Current pool size: " + tickets.size());
        notifyAll();
    }

    public synchronized void retrieveTickets(int count) {
        while (tickets.size() < count) {
            System.out.println("Not enough tickets. Customer waiting...");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        for (int i = 0; i < count; i++) {
            tickets.poll();
        }
        System.out.println(count + " tickets retrieved. Current pool size: " + tickets.size());
        notifyAll();
    }

    public synchronized int getPoolSize() {
        return tickets.size();
    }
}
