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
import org.springframework.stereotype.Component;

@Entity
@Component
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
//    @Setter
//    @Getter
    @Transient
    private TicketPool ticketPool;

    // Flag to control thread execution
    private volatile boolean running = true;

    public Vendor(long id, int releaseRate, TicketPool ticketPool) {
        this.id = id;
        this.releaseRate = releaseRate;
        this.ticketPool = ticketPool;
    }

    public Vendor() {
        // Default constructor for JPA
    }

    public void produceTickets() throws InterruptedException {
        int maxtickets = 0;
        while (maxtickets < 10) {
            ticketPool.addTicket(); // Ensure this is not null
            log.info("vendor, {} added a ticket.", id);
            maxtickets++;
            Thread.sleep(releaseRate*1000);
        }
        log.info("vendor, {} stopped producing tickets." , id);

    }

    public void stop() {
        this.running = false;
    }

}
