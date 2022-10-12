package com.solvd.busstation;

public class Edge {
    private Station target;
    private double distance;

    public Edge(Station target, double distance) {
        this.target = target;
        this.distance = distance;
    }

    public Station getTarget() {
        return target;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "target=" + target.getName() +
                ", distance=" + distance +
                '}';
    }
}
