package com.solvd.busstation.services;

import com.solvd.busstation.daoClasses.PassengerDAOImpl;
import com.solvd.busstation.models.passengers.Passenger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class PassengerServiceImpl {
    private final PassengerDAOImpl passengerDAOimpl = new PassengerDAOImpl();
    private static final Logger LOGGER = LogManager.getLogger(PassengerServiceImpl.class);

    public void createPassenger(Passenger p) {
        try {
            p.setId(passengerDAOimpl.create(p));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

}