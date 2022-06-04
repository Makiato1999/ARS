package com.flight.application;

import com.flight.persistence.CityCodesStub;
import com.flight.persistence.IHsqldbCityCodes;
import com.flight.persistence.FlightsStub;
import com.flight.persistence.IHsqldbFlights;
import com.flight.persistence.IHsqldbReservations;
import com.flight.persistence.hsqldb.CityCodePersistenceHSQLDB;
import com.flight.persistence.hsqldb.FlightPersistenceHSQLDB;
import com.flight.persistence.hsqldb.ReservationPersistenceHSQLDB;

public class Services {
    private static IHsqldbReservations rp = null;
    private static IHsqldbFlights fp = null;
    private static FlightsStub fh = null;
    private static IHsqldbCityCodes fakeDBPersistence = null;
    private static IHsqldbCityCodes ccp = null;

    public static synchronized FlightsStub getFlightHandler(){
        if (fh == null)
        {
            fh = new FlightsStub();
        }
        return fh;
    }

    public static synchronized IHsqldbFlights getFlightPersistence()
    {
        if (fp == null)
        {
            fp = new FlightPersistenceHSQLDB(Main.getDBPathName());
        }

        return fp;
    }

    public static synchronized IHsqldbReservations getReservationPersistence()
    {
        if (rp == null)
        {
            rp = new ReservationPersistenceHSQLDB(Main.getDBPathName());
        }

        return rp;
    }

    public static synchronized IHsqldbCityCodes getFakeDBPersistence()
    {
        if (fakeDBPersistence == null)
        {
            fakeDBPersistence = new CityCodesStub();
        }

        return fakeDBPersistence;
    }

    public static synchronized IHsqldbCityCodes getCityCodePersistence()
    {
        if (ccp == null)
        {
            ccp = new CityCodePersistenceHSQLDB(Main.getDBPathName());
        }

        return ccp;
    }
}
