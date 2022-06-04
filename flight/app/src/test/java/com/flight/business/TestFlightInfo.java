package com.flight.business;
import com.flight.objects.Flight;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestFlightInfo {
    private Flight f;
    private FlightsInfo fi;

    public void setUp(String departureCityCode, String arrivalCityCode, int distance) {
        f = new Flight(departureCityCode, arrivalCityCode, distance);
        fi = new FlightsInfo(f);
    }

    @BeforeClass
    public static void Test_starts() {
        System.out.println("Starting test TestFlightInfo\n");
    }

    @Test
    public void Test_getDistance() {
        System.out.println("Starting Test_getDistance\n");
        setUp("tempDcc", "tempAcc", 300);
        assertEquals(fi.getDistance(), f.getDistance());
        System.out.println("Finished Test_getDistance\n");
    }

    @Test
    public void Test_priceReturn_distance300() {
        System.out.println("Starting Test_priceReturn_distance300\n");
        setUp("tempDcc", "tempAcc", 300);
        assertTrue(fi.getPrice() >= 100 && fi.getPrice() <= 200);
        System.out.println("Finished Test_priceReturn_distance300\n");
    }
    @Test
    public void Test_priceReturn_distance800() {
        System.out.println("Starting Test_priceReturn_distance800\n");
        setUp("tempDcc", "tempAcc", 800);
        assertTrue(fi.getPrice() >= 200 && fi.getPrice() <= 300);
        System.out.println("Finished Test_priceReturn_distance800\n");
    }
    @Test
    public void Test_priceReturn_distance1300() {
        System.out.println("Starting Test_priceReturn_distance1300\n");
        setUp("tempDcc", "tempAcc", 1300);
        assertTrue(fi.getPrice() >= 300 && fi.getPrice() <= 400);
        System.out.println("Finished Test_priceReturn_distance1300\n");
    }
    @Test
    public void Test_priceReturn_distance1800() {
        System.out.println("Starting Test_priceReturn_distance1800\n");
        setUp("tempDcc", "tempAcc", 1800);
        assertTrue(fi.getPrice() >= 400 && fi.getPrice() <= 500);
        System.out.println("Finished Test_priceReturn_distance1800\n");
    }
    @Test
    public void Test_priceReturn_distance2300() {
        System.out.println("Starting Test_priceReturn_distance2300\n");
        setUp("tempDcc", "tempAcc", 2300);
        assertTrue(fi.getPrice() >= 500 && fi.getPrice() <= 600);
        System.out.println("Finished Test_priceReturn_distance2300\n");
    }
    @Test
    public void Test_priceReturn_distance2800() {
        System.out.println("Starting Test_priceReturn_distance2800\n");
        setUp("tempDcc", "tempAcc", 2800);
        assertTrue(fi.getPrice() >= 600 && fi.getPrice() <= 700);
        System.out.println("Finished Test_priceReturn_distance2800\n");
    }
    @Test
    public void Test_priceReturn_distance3300() {
        System.out.println("Starting Test_priceReturn_distance3300\n");
        setUp("tempDcc", "tempAcc", 3300);
        assertTrue(fi.getPrice() >= 700 && fi.getPrice() <= 800);
        System.out.println("Finished Test_priceReturn_distance3300\n");
    }
    @Test
    public void Test_priceReturn_distance3800() {
        System.out.println("Starting Test_priceReturn_distance3800\n");
        setUp("tempDcc", "tempAcc", 3800);
        assertTrue(fi.getPrice() >= 800 && fi.getPrice() <= 900);
        System.out.println("Finished Test_priceReturn_distance3800\n");
    }
    @Test
    public void Test_priceReturn_distance4300() {
        System.out.println("Starting Test_priceReturn_distance4300\n");
        setUp("tempDcc", "tempAcc", 4300);
        assertTrue(fi.getPrice() >= 900 && fi.getPrice() <= 1000);
        System.out.println("Finished Test_priceReturn_distance4300\n");
    }
    @Test
    public void Test_priceReturn_distance4800() {
        System.out.println("Starting Test_priceReturn_distance4800\n");
        setUp("tempDcc", "tempAcc", 4800);
        assertTrue(fi.getPrice() >= 1000 && fi.getPrice() <= 1100);
        System.out.println("Finished Test_priceReturn_distance4800\n");
    }

    @Test
    public void Test_getDepCity() {
        System.out.println("Starting Test_getDepCity\n");
        setUp("YYC", "WPG", 300);
        assertEquals(fi.getDepCity(), f.getDepartureCityCode());
        System.out.println("Finished Test_getDepCity\n");
    }
    @Test
    public void Test_getArrCity() {
        System.out.println("Starting Test_getArrCity\n");
        setUp("YYZ", "YVR", 1800);
        assertEquals(fi.getArrCity(), f.getArrivalCityCode());
        System.out.println("Finished Test_getArrCity\n");
    }

    @Test
    public void Test_getFlight() {
        System.out.println("Starting Test_getFlight\n");
        setUp("YYC", "WPG", 3200);
        assertEquals("YYC", fi.getFlight().getDepartureCityCode());
        assertEquals("WPG", fi.getFlight().getArrivalCityCode());
        assertEquals(3200, fi.getFlight().getDistance());
        System.out.println("Finished Test_getFlight\n");
    }

    @AfterClass
    public static void Test_finish() {
        System.out.println("Finished test TestFlightInfo\n");
    }
}
