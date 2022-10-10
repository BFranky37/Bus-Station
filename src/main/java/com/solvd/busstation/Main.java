package com.solvd.busstation;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        List<Station> list1 = Arrays.asList(new Station("Stop1"),new Station("Stop2"),new Station("Stop3"),new Station("Stop4"),new Station("Stop5"),new Station("Stop6"));
        Graph randomGraph = new Graph(list1);
        logger.info(randomGraph.adjacencyList);

        logger.info("Welcome to the bust station kiosk. We will help you plan a bus ride.");
        logger.info("First, choose the station you will be departing from:");
        for (int i = 0; i < list1.size(); i++) {
            logger.info(i+1 + ". " + list1.get(i).getName() + " (" + list1.get(i).getX_coordinate() + ", " + list1.get(i).getY_coordinate() + ")");
        }
        Station startPoint = list1.get(input.nextInt() - 1);
        input.nextLine();

        logger.info("Now, choose the station that will be your destination:");
        for (int i = 0; i < list1.size(); i++) {
            logger.info(i+1 + ". " + list1.get(i).getName() + " (" + list1.get(i).getX_coordinate() + ", " + list1.get(i).getY_coordinate() + ")");
        }
        Station endPoint = list1.get(input.nextInt() - 1);
        input.nextLine();
    }
}