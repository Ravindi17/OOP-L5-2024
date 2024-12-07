package com.TicketingSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Vendor {

    private static final Logger log = LoggerFactory.getLogger(Vendor.class);

    // Getters and setters
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private int releaseRate;

    // Transient field for runtime operations (not persisted in the database)
    @Setter
    @Getter
    @Transient
    private TicketPool ticketPool;

    // Flag to control thread execution
    private volatile boolean running = true;

    public Vendor(String name, int releaseRate, TicketPool ticketPool) {
        this.name = name;
        this.releaseRate = releaseRate;
        this.ticketPool = ticketPool;
    }

    public Vendor() {
        // Default constructor for JPA
    }

    public void produceTickets() throws InterruptedException {
        while (running) {
            ticketPool.addTicket(); // Ensure this is not null
            log.info(" {} added a ticket.", name);
            Thread.sleep(releaseRate);
        }
        log.info(" {} stopped producing tickets." , name);
    }

    public void stop() {
        this.running = false;
    }

}
