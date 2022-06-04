package com.flight.persistence.hsqldb;

import com.flight.objects.Reservation;
import com.flight.persistence.IHsqldbReservations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservationPersistenceHSQLDB implements IHsqldbReservations {

    private final String dbPath;

    public ReservationPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public void insert(Reservation saveThis) {
        /*Connection conn = null;
        String db = "jdbc:hsqldb:hsql://localhost/flights;ifexists=true";
        String user = "SA";
        String password = "";

        try(final Connection c = DriverManager.getConnection(db, user, password)){*/
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO RESERVATIONS VALUES(?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, saveThis.getEmail());
            st.setString(2, saveThis.getDate());
            st.setString(3, saveThis.getDepTime());
            st.setString(4, saveThis.getDeparture());
            st.setString(5, saveThis.getDepTime2());
            st.setString(6, saveThis.getDeparture2());
            st.setString(7, saveThis.getPrice());

            st.executeUpdate();

            final Statement state = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM RESERVATIONS WHERE EMAIL = 'jordon@hotmail.com'");

            if (rs.next()) {
                System.out.println(rs.getString("EMAIL") + rs.getString("DEPARTURE") + rs.getString("ARRIVAL") + Double.parseDouble(rs.getString("PRICE")) + rs.getString("DATE"));
            }

            rs.close();
            state.close();

        } catch (final SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public Reservation[] search(String email) {
        /*Connection conn = null;
        String db = "jdbc:hsqldb:hsql://localhost/flights;ifexists=true";
        String user = "SA";
        String password = "";*/
        int iterator = 0;
        Reservation[] target = new Reservation[howMany(email)];

        if(target.length > 0){
            //try(final Connection c = DriverManager.getConnection(db, user, password)){
            try (final Connection conn = connection()) {
                final Statement st = conn.createStatement();
                final ResultSet rs = st.executeQuery("SELECT * FROM RESERVATIONS WHERE EMAIL = '" + email +"'");

                while (rs.next()) {
                    target[iterator] = new Reservation(rs.getString("EMAIL"), rs.getString("DATE"), rs.getString("DEPARTURETIME"),rs.getString("DEPARTURE"), rs.getString("DEPARTURETIMETWO"),
                            rs.getString("DEPARTURETWO"), rs.getString("PRICE"));
                    iterator++;
                }

                rs.close();
                st.close();

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return target;
    }

    public int howMany(String email){
        //Connection conn = null;
        int recordCount = 0;
        /*String db = "jdbc:hsqldb:hsql://localhost/flights;ifexists=true";
        String user = "SA";
        String password = "";

        try(final Connection c = DriverManager.getConnection(db, user, password)){*/
        try (final Connection conn = connection()) {
            final Statement st = conn.createStatement();
            final ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM RESERVATIONS WHERE EMAIL = '" + email +"'");

            rs.next();
            recordCount = rs.getInt(1);

            rs.close();
            st.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return recordCount;
    }
}
