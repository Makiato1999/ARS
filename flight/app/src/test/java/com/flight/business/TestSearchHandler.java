package com.flight.business;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSearchHandler {
    private SearchHandler ss;


    public void setUp(String[] dep, String[] arr, String date) {
        ss = new SearchHandler(dep,arr,date);
    }

    @BeforeClass
    public static void Test_starts() {
        System.out.println("Starting test TestSearchHandler\n");
    }

    @Test
    public void Test_flightTableEmpty(){
        System.out.println("Starting Test_flightTableEmpty\n");
        setUp(new String[]{"Winnipeg", "YWG"}, new String[]{"Calgary","YYC"} ,"jan30");
        assertFalse(ss.handleFakeDB().getFlightTable().isEmpty());
        System.out.println("Finished Test_flightTableEmpty\n");
    }

    @Test
    public void Test_SearchHandlerDate(){
        System.out.println("Starting Test_SearchHandlerDate\n");
        setUp(new String[]{"Winnipeg", "YWG"}, new String[]{"Calgary","YYC"} ,"jan30");
        assertEquals(ss.getDate(),"jan30");
        System.out.println("Finished Test_SearchHandlerDate\n");
    }

    @AfterClass
    public static void Test_finish() {
        System.out.println("Finished test TestSearchHandler\n");
    }

}
