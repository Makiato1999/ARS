package com.flight.persistence.hsqldb;

import com.flight.objects.Flight;
import com.flight.persistence.IHsqldbFlights;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FlightPersistenceHSQLDB implements IHsqldbFlights {

    private final String dbPath;

    public FlightPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public Flight search(String departureCityCode, String arrivalCityCode) {
        Flight x = null;

        try (final Connection conn = connection()) {
            final Statement st = conn.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM FLIGHTS WHERE DEPART = '" + departureCityCode + "' AND ARRIVE = '" + arrivalCityCode + "'");
            //final ResultSet rs = st.executeQuery("SELECT * FROM FLIGHTS WHERE DEPART = 'YWG' AND ARRIVE = 'YYC'");

            if (rs.next()) {
                //System.out.println("departure: " + rs.getString("DEPART") + " arrive:" + rs.getString("ARRIVE") + " distance: " + rs.getString("DISTANCE"));
                x = new Flight(rs.getString("DEPART"), rs.getString("ARRIVE"), Integer.parseInt(rs.getString("DISTANCE")));
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return x;
    }

}
