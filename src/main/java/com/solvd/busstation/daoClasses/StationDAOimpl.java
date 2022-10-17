package com.solvd.busstation.daoClasses;

import com.solvd.busstation.models.Station;
import com.solvd.busstation.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationDAOimpl {
    private static final Logger LOGGER = LogManager.getLogger(StationDAOimpl.class);

    private static final String GET_BY_ID = "SELECT * FROM stations WHERE id = ?;";
    private static final String GET_ID_BY_STATION = "SELECT * FROM stations WHERE name = ? AND x_coordinate = ? AND y_coordinate = ?;";
    private static final String INSERT = "INSERT INTO stations (name, x_coordinate, y_coordinate) VALUES (?, ?, ?);";
    private static final String readAllQuery = "SELECT * FROM stations";


    public Station getObjectByID(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Station p = new Station(rs.getString("name"), rs.getDouble("x_coordinate"),
                        rs.getDouble("y_coordinate"));
                return p;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
        throw new SQLException("No data matching the ID given");
    }

    public int getIDbyObject(Station p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_ID_BY_STATION);
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getX_coordinate());
            ps.setDouble(3, p.getY_coordinate());
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
        throw new SQLException("No data matching the Object given");
    }

    public int create(String name, double x_coord, double y_coord) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(INSERT);
            ps.setString(1, name);
            ps.setDouble(2, x_coord);
            ps.setDouble(3, y_coord);
            ps.executeUpdate();

            Station s = new Station(name, x_coord, y_coord);
            return getIDbyObject(s);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            assert ps != null;
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
        throw new SQLException("Could not get ID for this object");
    }

    public List<Station> getAllStations() {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Station> stations = new ArrayList<>();
        try(PreparedStatement ps =con.prepareStatement(readAllQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Station station = new Station(rs.getString("name"), rs.getDouble("x_coordinate"),
                        rs.getDouble("y_coordinate"));
                stations.add(station);
            }
        } catch (SQLException e) {
            LOGGER.error("Getting all records from Stations Table Failed");
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return stations;
    }
}
