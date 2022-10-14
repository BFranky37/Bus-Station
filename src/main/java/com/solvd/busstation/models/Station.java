package com.solvd.busstation.models;

import com.solvd.busstation.services.EdgeServiceImpl;
import com.solvd.busstation.services.StationServiceImpl;

import java.util.ArrayList;
import java.util.Objects;

public class Station implements  Comparable<Station> {
    private final StationServiceImpl stationService = new StationServiceImpl();
    private final EdgeServiceImpl edgeService = new EdgeServiceImpl();

    private final double min = -50.0;
    private final double max = 50.0 ;

    int id;
    private String name;
    private double x_coordinate;
    private double y_coordinate;
    private double minDistance = Double.POSITIVE_INFINITY;
    private ArrayList<Edge> edges = new ArrayList();
    private Station previous;

    public Station(String name) {
        this.name = name;
        this.x_coordinate = ((Math.random() * (max - min)) + min);;
        this.y_coordinate = ((Math.random() * (max - min)) + min);

        id = stationService.createStation(name, x_coordinate, y_coordinate);
    }

    public Station(String name, double x, double y) {
        this.name = name;
        this.x_coordinate = x;
        this.y_coordinate = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public Station getPrevious() {
        return previous;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    public void setPrevious(Station previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "Station:" +
                name + '\'' +
                " (" + x_coordinate +
                ", " + y_coordinate + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, x_coordinate, y_coordinate);
    }

    @Override
    public int compareTo(Station o) {
        return Double.compare(minDistance, o.getMinDistance());
    }
    public void addEdge(Station target){
        double x1 = this.x_coordinate;
        double y1 = this.y_coordinate;
        double x2 = target.getX_coordinate();
        double y2 = target.getY_coordinate();
        double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        this.edges.add(new Edge(target,distance));

        //add edge to database
        edgeService.createEdge(id, target.getId(), distance);
    }
}
