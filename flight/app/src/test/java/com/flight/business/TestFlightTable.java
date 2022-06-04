package com.flight.business;
import com.flight.objects.Flight;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestFlightTable {
    private Flight f;
    private FlightsInfo fi;
    private FlightTable flt;

    public void setUp(String departureCityCode, String arrivalCityCode, int distance) {
        f = new Flight(departureCityCode, arrivalCityCode, distance);
        fi = new FlightsInfo(f);
        flt = new FlightTable(fi);
    }

    @BeforeClass
    public static void Test_starts() {
        System.out.println("Starting test TestFlightTable\n");
    }

    @Test
    public void Test_getArrayList_size() {
        System.out.println("Starting Test_getArrayList_size\n");
        setUp("YYC", "YOW", 2300);
        assertFalse(flt.getFlightTable().isEmpty());
        assertTrue(flt.getFlightTable().size() == 5);
        System.out.println("Finished Test_getArrayList_size\n");
    }

    @Test
    public void Test_getArrayList_content() {
        System.out.println("Starting Test_getArrayList_content\n");
        setUp("YYC", "YOW", 2300);
        for (int i = 0; i < flt.getFlightTable().size(); i++) {
            assertEquals(flt.getFlightTable().get(i).getDistance(), 2300);
            assertEquals(flt.getFlightTable().get(i).getDepCity(), "YYC");
            assertEquals(flt.getFlightTable().get(i).getArrCity(), "YOW");
        }
        System.out.println("Finished Test_getArrayList_content\n");
    }

    @AfterClass
    public static void Test_finish() {
        System.out.println("Finished test TestFlightTable\n");
    }
}
