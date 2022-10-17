package com.solvd.busstation.services;

import com.solvd.busstation.daoClasses.StationDAOimpl;
import com.solvd.busstation.models.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class StationServiceImpl {
    private static final Logger LOGGER = LogManager.getLogger(StationDAOimpl.class);
    private final StationDAOimpl stationDAOimpl = new StationDAOimpl();

    public int createStation(String name, double x_coord, double y_coord) {
        try {
            return stationDAOimpl.create(name, x_coord, y_coord);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    public List<Station> getAllStations() {
        List<Station> stations = null;
        stations = stationDAOimpl.getAllStations();
        return stations;
    }

    public int getIdByStations(Station p){
        try {
            return stationDAOimpl.getIDbyObject(p);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return 0;
    }

}
