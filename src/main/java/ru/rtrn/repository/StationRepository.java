package ru.rtrn.repository;

import ru.rtrn.entity.Station;

import java.sql.*;
import java.util.ArrayList;

public class StationRepository {
    private final String driverName = "org.sqlite.JDBC";
    private final String dbName = "jdbc:sqlite:stations.db";
    private ArrayList<Station> stationList;
    Connection connection;

    public StationRepository() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(dbName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public ArrayList<String> getNames() throws SQLException {
        String sql = "SELECT name FROM stations";
        ArrayList<String> stationNames = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                stationNames.add(resultSet.getString("name"));
            }
        }
        return stationNames;
    }

    public Station getStationByName(String stationName) {
        String sql = "SELECT name, ip, devices FROM stations WHERE name = ?";
        Station station = new Station();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, stationName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                station.setName(resultSet.getString("name"));
                station.setIp(resultSet.getString("ip"));
                if (resultSet.getString("devices")!=null){
                station.setDevicesByString(resultSet.getString("devices"));}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return station;
    }


}
