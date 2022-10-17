package com.solvd.busstation.daoClasses;
import com.solvd.busstation.models.passengers.Profile;
import com.solvd.busstation.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDAOImpl {
    private static final Logger LOGGER = LogManager.getLogger(ProfileDAOImpl.class);
    private static final String GET_ID_BY_PROFILE = "SELECT * FROM profiles WHERE first_name = ? AND last_name = ? AND phone_number = ?;";
    private static final String INSERT = "INSERT INTO profiles (first_name, last_name, phone_number) VALUES (?, ?, ?);";

    public int getIDbyObject(Profile p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_ID_BY_PROFILE);
            ps.setString(1, p.getFirstName());
            ps.setString(2, p.getLastName());
            ps.setString(3, p.getPhoneNumber());
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
        throw new SQLException("No matching data given");
    }

    public int create(Profile p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(INSERT);
            ps.setString(1, p.getFirstName());
            ps.setString(2, p.getLastName());
            ps.setString(3, p.getPhoneNumber());
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
