package com.TicketingSystem.systemusers;

public class Ticket {
    private String name;
    private String description;

    // Constructors, getters, and setters
    public Ticket(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Ticket(int nextTicketID) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
