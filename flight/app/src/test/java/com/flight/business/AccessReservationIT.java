package com.flight.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.flight.persistence.IHsqldbReservations;
import com.flight.persistence.hsqldb.ReservationPersistenceHSQLDB;
import com.flight.utils.TestUtils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AccessReservationIT {
    private ResSearchHandler resSearchHandler;
    private File tempDB;
    private ResInfo resInfo;

    @BeforeClass
    public static void Test_starts() {
        System.out.println("Starting Integration Test AccessReservationIT\n");
    }

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final IHsqldbReservations persistence = new ReservationPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.resSearchHandler = new ResSearchHandler(persistence, "YWG@gmail.com");
        resInfo = new ResInfo("YWG@gmail.com", "jan30", "02:00", "YWG","04:50", "YYC", "100", persistence);
    }

    @Test
    public void testSinglereservation() {
        resInfo.addResInfo();
        ArrayList<ResInfo> table = resSearchHandler.getResTable();
        assertFalse(table.isEmpty());
        assertEquals(1, table.size());
        assertEquals("YWG@gmail.com",table.get(0).getEmail());
        assertEquals("jan30",table.get(0).getDate());
        assertEquals("02:00",table.get(0).getdepTime());
        assertEquals("YWG",table.get(0).getDepart());
        assertEquals("04:50",table.get(0).getdepTime2());
        assertEquals("YYC",table.get(0).getDepart2());
        assertEquals("100",table.get(0).getPrice());
        System.out.println("Finished test testSinglereservation");
    }

    @Test
    public void testIsolateDates(){
        resInfo.addResInfo();
        ArrayList<ResInfo> table = resSearchHandler.getResTable();
        assertFalse(table.isEmpty());
        assertEquals(1, table.size());
        assertEquals("jan30",table.get(0).getDate());
        System.out.println("Finished test testIsolateDates");
    }

    @Test
    public void testIsolateCities(){
        resInfo.addResInfo();
        ArrayList<ResInfo> table = resSearchHandler.getResTable();
        assertFalse(table.isEmpty());
        assertEquals("YWG",table.get(0).getDepart());
        assertEquals("YYC",table.get(0).getDepart2());
        System.out.println("Finished test testIsolateCities");

    }

    @Test
    public void testIsolateEmail(){
        resInfo.addResInfo();
        ArrayList<ResInfo> table = resSearchHandler.getResTable();
        assertFalse(table.isEmpty());
        assertEquals("YWG@gmail.com",table.get(0).getEmail());
        System.out.println("Finished test testIsolateEmail");
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

    @AfterClass
    public static void Test_finish() {
        System.out.println("Finishing Integration Test AccessReservationIT\n");
    }
}
