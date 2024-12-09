package com.TicketingSystem.model;

import jakarta.persistence.*;
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

    @Transient
    private TicketPool ticketPool;

    public Customer(String name, int retrievalRate , TicketPool ticketPool) {
        this.name = name;
        this.retrievalRate = retrievalRate;
        this.ticketPool = ticketPool;
    }

    public Customer() {
    }

    public void consumeTickets(TicketPool ticketPool) throws InterruptedException {
        int maxvalue = 0;
        while (maxvalue < 10) {
            int ticketId = ticketPool.removeTicket();
            log.info(" {} retrieved ticket ID: {} " , name ,ticketId);
            maxvalue++;
            Thread.sleep(retrievalRate);
        }
    }


}
