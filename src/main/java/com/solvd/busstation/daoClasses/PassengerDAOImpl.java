package com.solvd.busstation.daoClasses;

import com.solvd.busstation.models.passengers.Passenger;
import com.solvd.busstation.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerDAOImpl {
    private static final Logger LOGGER = LogManager.getLogger(EdgeDAOimpl.class);
    private static final String GET_ID_BY_PASSENGER = "SELECT * FROM passengers WHERE profileID = ? AND ticketID = ?;";
    private static final String INSERT = "INSERT INTO passengers (profileID, ticketID) VALUES (?, ?);";

    public int getIDbyObject(Passenger p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_ID_BY_PASSENGER);
            ps.setInt(1, p.getProfileId());
            ps.setInt(2, p.getTicketId());
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
        throw new SQLException("No matching data");
    }

    public int create(Passenger p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(INSERT);
            ps.setInt(1, p.getProfileId());
            ps.setInt(2, p.getTicketId());
            ps.executeUpdate();
            return getIDbyObject(p);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            assert ps != null;
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
        throw new SQLException("Could not create an object");
    }
}