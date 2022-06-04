package com.flight.business;
import com.flight.objects.Reservation;
import com.flight.persistence.IHsqldbReservations;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class TestResInfo {
    private ResInfo resInfo;
    private Reservation res;
    private IHsqldbReservations fkDB;
    private String email = "stranger@hotmail.com";
    private String date = "20220101";
    private String depTime = "20220101 01:01";
    private String depart = "Winnipeg";
    private String depTime2 = "20220101 03:03";
    private String depart2 = "Calgary";
    private String price = "200";
    private Reservation[] resArray;

    @BeforeClass
    public static void Test_starts() {
        System.out.println("Starting test TestResInfo\n");
    }
    @Before
    public void setUp() {
        fkDB = Mockito.mock(IHsqldbReservations.class);
        res = Mockito.mock(Reservation.class);
        resInfo = new ResInfo(email, date, depTime, depart, depTime2, depart2, price, fkDB);
    }
    @Test
    public void Test_getEmail() {
        System.out.println("Starting Test_getEmail\n");
        when(res.getEmail()).thenReturn(email);
        assertTrue(resInfo.getEmail() == email);
        System.out.println("Finished Test_getEmail\n");
    }
    @Test
    public void Test_getDate() {
        System.out.println("Starting Test_getDate\n");
        when(res.getDate()).thenReturn(date);
        assertTrue(resInfo.getDate() == date);
        System.out.println("Finished Test_getDate\n");
    }
    @Test
    public void Test_getdepTime() {
        System.out.println("Starting Test_getdepTime\n");
        when(res.getDepTime()).thenReturn(depTime);
        assertTrue(resInfo.getdepTime() == depTime);
        System.out.println("Finished Test_getdepTime\n");
    }
    @Test
    public void Test_getDepart() {
        System.out.println("Starting Test_getDepart\n");
        when(res.getDeparture()).thenReturn(depart);
        assertTrue(resInfo.getDepart() == depart);
        System.out.println("Finished Test_getDepart\n");
    }
    @Test
    public void Test_getdepTime2() {
        System.out.println("Starting Test_getdepTime2\n");
        when(res.getDepTime2()).thenReturn(depTime2);
        assertTrue(resInfo.getdepTime2() == depTime2);
        System.out.println("Finished Test_getdepTime2\n");
    }
    @Test
    public void Test_getDepart2() {
        System.out.println("Starting Test_getDepart2\n");
        when(res.getDeparture2()).thenReturn(depart2);
        assertTrue(resInfo.getDepart2() == depart2);
        System.out.println("Finished Test_getDepart2\n");
    }
    @Test
    public void Test_getPrice() {
        System.out.println("Starting Test_getPrice\n");
        when(res.getPrice()).thenReturn(price);
        assertTrue(resInfo.getPrice() == price);
        System.out.println("Finished Test_getPrice\n");
    }
    @Test
    public void Test_getResInfo() {
        System.out.println("Starting Test_getResInfo\n");
        assertTrue(resInfo.getResInfo() != null);
        System.out.println("Finished Test_getResInfo\n");
    }
    @Test
    public void Test_addResInfo() {
        System.out.println("Starting Test_addResInfo\n");
        fkDB.insert(res);
        verify(fkDB).insert(res);
        System.out.println("Finished Test_addResInfo\n");
    }
    @AfterClass
    public static void Test_finish() {
        System.out.println("Finished test TestResInfo\n");
    }
}
