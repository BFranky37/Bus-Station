package com.solvd.busstation;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        List<Station> list1 = Arrays.asList(new Station("Stop1"),new Station("Stop2"),new Station("Stop3"),new Station("Stop4"),new Station("Stop5"),new Station("Stop6"));
        Graph randomGraph = new Graph(list1);
        logger.info(randomGraph.adjacencyList);
    }
}