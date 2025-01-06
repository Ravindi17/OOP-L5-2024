package com.TicketingSystem.model;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketPool {
	private final ConcurrentLinkedQueue<Integer> tickets;
	private final int maxTicketCapacity;

	public TicketPool(int totalTickets, int maxTicketCapacity) {
		this.tickets = new ConcurrentLinkedQueue<>();
		this.maxTicketCapacity = maxTicketCapacity;
		// Pre-fill the queue with initial tickets
		for (int i = 0; i < totalTickets; i++) {
			tickets.offer(i);
		}
	}

	// Add tickets to the pool (Vendor/Producer)
	public synchronized void addTickets(int count) {
		for (int i = 0; i < count; i++) {
			if (tickets.size() < maxTicketCapacity) {
				tickets.offer(tickets.size() + 1); // Ticket ID
			}
		}
	}

	// Remove tickets from the pool (Customer/Consumer)
	public synchronized Integer removeTicket() {
		return tickets.poll();
	}

	public int getTicketCount() {
		return tickets.size();
	}
}

