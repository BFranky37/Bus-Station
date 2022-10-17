package com.solvd.busstation;


import com.solvd.busstation.models.Station;
import com.solvd.busstation.services.StationServiceImpl;
import com.solvd.busstation.utils.Display;
import com.solvd.busstation.utils.ShortestPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final Scanner input = new Scanner(System.in);
    private static void createObjects(Class classRef, int numObjects){
        switch(classRef.getSimpleName()) {
            case "Station":
                IntStream.range(0, numObjects)
                        .forEach(
                                p -> {
                                    new Station(String.format("stop%d", p+1));
                                }
                        );
        }
    }

    public static void main(String[] args) {
        createObjects(Station.class, 10);
        Graph randomGraph = new Graph();
        List<Station> chosenSubset = randomGraph.getStations();
        logger.info(randomGraph.adjacencyList);

        logger.info("Welcome to the bust station kiosk. We will help you plan a bus ride.");
        logger.info("First, choose the station you will be departing from:");
        Display.printList(chosenSubset);
        Station startPoint = chosenSubset.get(input.nextInt() - 1);
        input.nextLine();
        ShortestPath.computePaths(startPoint);
        logger.info("Now, choose the station that will be your destination:");
        Display.printList(chosenSubset);
        Station endPoint = chosenSubset.get(input.nextInt() - 1);
        List<String> path = ShortestPath.getShortestPathTo(endPoint);
        logger.info(path);
    }
}