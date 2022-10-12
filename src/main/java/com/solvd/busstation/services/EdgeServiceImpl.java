package com.solvd.busstation.services;

import com.solvd.busstation.daoClasses.EdgeDAOimpl;
import com.solvd.busstation.daoClasses.StationDAOimpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class EdgeServiceImpl {
    private static final Logger LOGGER = LogManager.getLogger(EdgeServiceImpl.class);
    private final EdgeDAOimpl edgeDAOimpl = new EdgeDAOimpl();

    public void createEdge(int sourceID, int targetID, double distance) {
        try {
            edgeDAOimpl.create(sourceID, targetID, distance);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
