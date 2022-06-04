package com.flight.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.flight.persistence.IHsqldbFlights;
import com.flight.persistence.hsqldb.FlightPersistenceHSQLDB;
import com.flight.utils.TestUtils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AccessFlightIT {
    private FlightTable flightTable;
    private SearchHandler ss;
    private File tempDB;

    @BeforeClass
    public static void Test_starts() {
        System.out.println("Starting Integration Test AccessFlightIT\n");
    }
    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final IHsqldbFlights persistence = new FlightPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        ss = new SearchHandler(new String[]{"Winnipeg", "YWG"}, new String[]{"Calgary","YYC"} ,"jan30",persistence);

    }

    @Test
    public void testSearchDate() {

        ArrayList<FlightsInfo> table = ss.handleRealDB().getFlightTable();

        assertFalse(table.isEmpty());
        assertTrue("jan30".equals(ss.getDate()));
        System.out.println("Finished test testSearchDate");
    }


    @Test
    public void testFlightSize(){
        ArrayList<FlightsInfo> table = ss.handleRealDB().getFlightTable();
        assertFalse(table.isEmpty());
        assertEquals(5, table.size());
        System.out.println("Finished test testFlightSize");
    }

    @Test
    public void testFlightDep(){
        ArrayList<FlightsInfo> table = ss.handleRealDB().getFlightTable();
        assertFalse(table.isEmpty());
        assertTrue("YWG".equals(table.get(0).getDepCity()));
        System.out.println("Finished test testFlightDep");
    }

    @Test
    public void testFlightArr(){
        ArrayList<FlightsInfo> table = ss.handleRealDB().getFlightTable();
        assertFalse(table.isEmpty());
        assertTrue("YYC".equals(table.get(0).getArrCity()));
        System.out.println("Finished test testFlightArr");
    }


    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

    @AfterClass
    public static void Test_finish() {
        System.out.println("Finishing Integration Test AccessFlightIT\n");
    }

}
