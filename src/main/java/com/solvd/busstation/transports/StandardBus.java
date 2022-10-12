package com.solvd.busstation.transports;

public class StandardBus implements Bus{

    private int busId;
    private int passengerMaxSize;
    private double speed;
    private int currPassengers;

    public StandardBus(int busId, int passengerMaxSize, double speed) {
        this.busId = busId;
        this.passengerMaxSize = passengerMaxSize;
        this.speed = speed;
        currPassengers = 0;
    }

    @Override
    public int getBusID() {
        return busId;
    }

    @Override
    public int getPassengerMaxSize() {
        return passengerMaxSize;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int getCurrPassengers() {
        return 0;
    }

    @Override
    public void increasePassengers(int n) {
        currPassengers += n;
    }

    @Override
    public void decreasePassengers(int n) {
        currPassengers -=n;
    }

    @Override
    public void setPassengerMaxSize(int passengerMaxSize) {
        this.passengerMaxSize = passengerMaxSize;
    }

    @Override
    public double getSpeed() {
        return speed;
    }
}
