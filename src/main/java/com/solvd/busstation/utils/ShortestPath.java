package com.solvd.busstation.utils;

import com.solvd.busstation.models.Edge;
import com.solvd.busstation.models.Station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class ShortestPath {
    public static void computePaths(Station source)
    {
        source.setMinDistance(0);
        PriorityQueue<Station> pq = new PriorityQueue<Station>();
        pq.add(source);

        while (!pq.isEmpty()) {
            Station u = pq.poll();

            // Visit each edge exiting u
            for (Edge e : u.getEdges())
            {
                Station v = e.getTarget();
                double distance = e.getDistance();
                double distanceThroughU = u.getMinDistance() + distance;
                if (distanceThroughU < v.getMinDistance()) {
                    pq.remove(v);
                    v.setMinDistance(distanceThroughU) ;
                    v.setPrevious(u);
                    pq.add(v);
                }
            }
        }
    }
    public static List<String> getShortestPathTo(Station target)
    {
        List<String> path = new ArrayList<String>();
        Station station = target;
        while (station != null) {
            path.add(station.getName());
            station = station.getPrevious();
        }
        Collections.reverse(path);
        return path;
    }
}
