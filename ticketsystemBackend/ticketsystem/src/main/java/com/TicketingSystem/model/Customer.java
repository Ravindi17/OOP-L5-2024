package com.TicketingSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Setter
@Getter
@Entity
public class Customer {

    private static final Logger log = LoggerFactory.getLogger(Customer.class);
    // Getters and setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int retrievalRate;

    public Customer(String name, int retrievalRate , TicketPool ticketPool) {
        this.name = name;
        this.retrievalRate = retrievalRate;
    }

    public Customer() {
    }

    public void consumeTickets(TicketPool ticketPool) throws InterruptedException {
        while (true) {
            int ticketId = ticketPool.removeTicket();
            log.info(" {} retrieved ticket ID: {} " , name ,ticketId);
            Thread.sleep(retrievalRate);
        }
    }


}
