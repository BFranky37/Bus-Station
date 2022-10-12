package com.solvd.busstation.models.passengers;

import com.solvd.busstation.models.trips.Ticket;

public class Passenger {

    private int passID;
    private Contact contact;
    private Ticket ticket;

    public Passenger(int passID, Contact contact, Ticket ticket) {
        this.passID = passID;
        this.contact = contact;
        this.ticket = ticket;
    }

    public int getPassID() {
        return passID;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
