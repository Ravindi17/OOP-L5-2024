package com.TicketingSystem.systemusers;

public class Ticket {
    private int ID;

    public Ticket(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Ticket ID = " + ID ;
    }
}
