package com.solvd.busstation.models.trips;

public class Ticket {

    private int ticketID;
    private double price;
    // TODO private Destination???

    public Ticket(int ticketID, double price) {
        this.ticketID = ticketID;
        this.price = price;
    }

    public int getTicketID() {
        return ticketID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
