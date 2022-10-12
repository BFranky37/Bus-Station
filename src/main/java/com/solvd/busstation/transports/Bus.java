package com.solvd.busstation.transports;

public interface Bus {

    // TODO route? something like NYC subway like routes (red, yellow blue...)
    //  coule be in here or elsewhere

    public int getBusID();
    public int getPassengerMaxSize();
    public boolean isFull();
    public int getCurrPassengers();
    public void increasePassengers(int n);
    public void decreasePassengers(int n);
    public void setPassengerMaxSize(int passengerMaxSize);
    public double getSpeed();

}
