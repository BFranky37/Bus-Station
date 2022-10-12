package com.solvd.busstation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.IntStream;

public class Graph {
    private int numPoints;
    private static final Logger logger = LogManager.getLogger(Graph.class);
    private int numEdges;
    private List<Station> stations;
    Set<Integer> connected = new HashSet<Integer>();
    Set<Integer> notConnected = new HashSet<Integer>();

    Random random = new Random();

    // An adjacency list to represent a graph
    public DefaultDict<Station, List<Station>> adjacencyList =
            new DefaultDict<Station, List<Station>>(ArrayList.class);



    public Graph(List points)

    {
        if (points.size()<3){
            logger.warn("There has to be at least three points");
            return;
        }

        this.stations = new ArrayList<>(points);

        this.numPoints = random.nextInt(points.size()-3) + 3; //making sure we have at least three points
        logger.info("number of points");
        logger.info(this.numPoints);

        connected.add(0);
        IntStream.range(1, this.numPoints).forEach(i -> notConnected.add(i));

        // at least P and at most P*(P-1)/2 number of edges
        this.numEdges = random.nextInt(((this.numPoints * (this.numPoints - 1) )/ 2) - this.numPoints + 1 ) + this.numPoints - 1;
        logger.info("number of edges");
        logger.info(this.numEdges);

        while (!(notConnected.isEmpty())){         //making sure all points would be connected
            int a = getRandomElement(notConnected);
            int b = getRandomElement(connected);
            add_edge(a,b);
            notConnected.remove(a);
            connected.add(a);
        }

        while (this.numEdges > 0) {    //randomly generate edges

            int a = random.nextInt(numPoints);
            int b = random.nextInt(numPoints);
            // Check if a and b are the same or there is already an edge between them
            if ((a == b) || (adjacencyList.get(stations.get(a)).contains(stations.get(b)))) {
                continue;
            }
            add_edge(a,b);
        }
    }
    void add_edge(int a,int b){
        Station station1 = stations.get(a);
        Station station2 = stations.get(b);
        connected.add(a);
        connected.add(b);
        adjacencyList.get(station1).add(station2);
        adjacencyList.get(station2).add(station1);
        station1.addEdge(station2);
        station2.addEdge(station1);
        this.numEdges -= 1;
    }

    int getRandomElement(Set hashSet){
        int index = random.nextInt(hashSet.size());
        Iterator<Integer> iter = hashSet.iterator();
        for (int i = 0; i < index; i++) {
            iter.next();
        }
        return iter.next();
    }
}
