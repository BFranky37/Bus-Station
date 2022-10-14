package com.solvd.busstation;


import com.solvd.busstation.models.Station;
import com.solvd.busstation.utils.Display;
import com.solvd.busstation.utils.ShortestPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        List<Station> list1 = Arrays.asList(new Station("Stop1"),new Station("Stop2"),new Station("Stop3"),new Station("Stop4"),new Station("Stop5"),new Station("Stop6"));
        Graph randomGraph = new Graph(list1);
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