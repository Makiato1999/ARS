package com.flight.persistence;

import com.flight.application.Services;
import com.flight.objects.Flight;
import com.flight.persistence.IHsqldbFlights;
import com.flight.persistence.hsqldb.FlightPersistenceHSQLDB;
import com.flight.utils.TestUtils;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TestFlightSearch {
    IHsqldbFlights persistence;
    private File tempDB;

    @BeforeClass
    public static void Test_starts() {
        System.out.println("Starting test TestFlightSearch\n");
    }

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        persistence = new FlightPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
    }

    @Test
    public void searchFlight(){
        System.out.println("Starting searchFlight\n");
        Flight x = persistence.search("YWG","YUL");
        assert(x.getDepartureCityCode().equals("YWG") && x.getArrivalCityCode().equals("YUL") && x.getDistance() == 1818);
        x = persistence.search("YVR","YYZ");
        assert(x.getDepartureCityCode().equals("YVR") && x.getArrivalCityCode().equals("YYZ") && x.getDistance() == 3345);
        System.out.println("Finished searchFlight\n");
    }

    @Test
    public void returnNull(){
        System.out.println("Starting returnNull\n");
        Flight x = null;
        x = persistence.search("NOT","HERE");
        assert(x == null);
        System.out.println("Finished returnNull\n");
    }

    @AfterClass
    public static void Test_finish() {
        System.out.println("Finished test TestFlightSearch\n");
    }
}
