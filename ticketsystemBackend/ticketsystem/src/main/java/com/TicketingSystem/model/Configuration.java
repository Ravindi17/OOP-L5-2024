package com.TicketingSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    // Getters and setters
    @Setter
    @Getter
    private int maximumTicketCapacity;
    @Setter
    @Getter
    private int numVendors;
    @Setter
    @Getter
    private int numCustomers;
    @Setter
    @Getter
    private int ticketReleaseRate;
    @Setter
    @Getter
    private int customerRetrievalRate;
    @Setter
    @Getter
    private int totalTickets;

    public Configuration() {
    }

    public Configuration(int maximumTicketCapacity, int numVendors, int numCustomers, int ticketReleaseRate, int customerRetrievalRate , int totalTickets) {
        this.maximumTicketCapacity = maximumTicketCapacity;
        this.numVendors = numVendors;
        this.numCustomers = numCustomers;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.totalTickets = totalTickets;
    }

}



