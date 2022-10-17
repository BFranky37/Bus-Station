package com.solvd.busstation.services;

import com.solvd.busstation.daoClasses.ProfileDAOImpl;
import com.solvd.busstation.models.passengers.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class ProfileServiceImpl {
    private final ProfileDAOImpl profileDAOimpl = new ProfileDAOImpl();
    private static final Logger LOGGER = LogManager.getLogger(ProfileServiceImpl.class);
    private Scanner input = new Scanner(System.in);

    public int getIDbyProfile(Profile p) {
        try {
            return profileDAOimpl.getIDbyObject(p);
        } catch (SQLException e) {
            return -1;
        }
    }
    public void createProfile(Profile p) {
        try {
            p.setId(profileDAOimpl.create(p));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public Profile addNewUser() {
        LOGGER.info("Please enter your first name: ");
        String firstName = input.nextLine();
        LOGGER.info("Please enter your last name: ");
        String lastName = input.nextLine();
        LOGGER.info("Please enter your phone number: ");
        String phoneNumber = input.nextLine();

        Profile p = new Profile(firstName, lastName, phoneNumber);

        try {
            p.setId(profileDAOimpl.create(p));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return p;
    }

}