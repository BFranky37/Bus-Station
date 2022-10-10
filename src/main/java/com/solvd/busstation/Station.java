package com.solvd.busstation;

import java.util.Objects;

public class Station {
    private final double min = -50.0;
    private final double max = 50.0 ;

    private String name;
    private double x_coordinate;
    private double y_coordinate;

    public Station(String name) {
        this.name = name;
        this.x_coordinate = ((Math.random() * (max - min)) + min);;
        this.y_coordinate = ((Math.random() * (max - min)) + min);
    }

    public String getName() {
        return name;
    }

    public double getX_coordinate() {
        return x_coordinate;
    }

    public double getY_coordinate() {
        return y_coordinate;
    }

    @Override
    public String toString() {
        return "Station:" +
                "name='" + name + '\'' +
                ", x_coordinate=" + x_coordinate +
                ", y_coordinate=" + y_coordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, x_coordinate, y_coordinate);
    }
}
