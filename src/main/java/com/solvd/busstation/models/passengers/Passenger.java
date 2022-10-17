package com.solvd.busstation.models.passengers;

public class Passenger {

    private int id;
    private int profileId;
    private int ticketId;

    public Passenger(int id) {
    }

    public Passenger(int profileId, int ticketId) {
        this.profileId = profileId;
        this.ticketId = ticketId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
}