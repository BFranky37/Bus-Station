package com.solvd.busstation.daoClasses;

import com.solvd.busstation.models.Edge;
import com.solvd.busstation.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EdgeDAOimpl {
    private static final Logger LOGGER = LogManager.getLogger(EdgeDAOimpl.class);

    private static final String INSERT = "INSERT INTO edges (sourceID, targetID, distance) VALUES (?, ?, ?);";


    public void create(int sourceID, int targetID, double distance) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(INSERT);
            ps.setInt(1, sourceID);
            ps.setInt(2, targetID);
            ps.setDouble(3, distance);
            ps.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            assert ps != null;
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
    }
}
