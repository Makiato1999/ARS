package com.flight.persistence.hsqldb;

import android.util.Log;

import com.flight.objects.CityCode;
import com.flight.objects.Flight;
import com.flight.objects.CityCode;
import com.flight.persistence.CityCodesStub;
import com.flight.persistence.IHsqldbCityCodes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CityCodePersistenceHSQLDB implements IHsqldbCityCodes {

    private final String dbPath;
    private final int ARRAYSIZE = 13;

    public CityCodePersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public CityCode search(String name) {
        /*String db = "jdbc:hsqldb:hsql://localhost/flights;ifexists=true";
        String user = "SA";
        String password = "";*/
        //returns null if string not found in db
        CityCode target = null;

        if(name.equalsIgnoreCase("St. John's")){name = "YYT";}

        //try(final Connection conn = DriverManager.getConnection(db, user, password);){
        try (final Connection conn = connection()) {
            final Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CITYCODES WHERE NAME = '"+name+"'");

            if (rs.next()) {
                target = new CityCode(rs.getString("NAME"),rs.getString("CODE"));
            }
            else{
                rs = st.executeQuery("SELECT * FROM CITYCODES WHERE CODE = '"+name+"'");
                if(rs.next()){
                    target = new CityCode(rs.getString("NAME"),rs.getString("CODE"));
                }
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return target;
    }

    @Override
    public void print() {
        CityCode[] output = getArray();
        for(int i = 0; i < ARRAYSIZE; i++) {
            output[i].print();
        }
    }

    @Override
    public CityCode[] getArray() {
        CityCode[] returnArray = null;

        /*String db = "jdbc:hsqldb:hsql://localhost/flights;ifexists=true";
        String user = "SA";
        String password = "";

        try(final Connection conn = DriverManager.getConnection(db, user, password);){*/
        try (final Connection conn = connection()) {
            final Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CITYCODES");

            if (rs.next()) {
                returnArray = new CityCode[ARRAYSIZE];
                for(int i = 0; i < ARRAYSIZE; i++){
                    returnArray[i] = new CityCode(rs.getString("NAME"),rs.getString("CODE"));
                    rs.next();
                }
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return returnArray;
    }
}
