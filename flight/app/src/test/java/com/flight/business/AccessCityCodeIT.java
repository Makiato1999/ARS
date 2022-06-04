package com.flight.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.flight.objects.CityCode;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class AccessCityCodeIT {
    private AccessCityCode accessCityCode;
    private File tempDB;


    @Before
    public void setUp() throws IOException {
        // this.tempDB = TestUtils.copyDB();
        // final CoursePersistence persistence = new CoursePersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessCityCode = new AccessCityCode(true);
    }

    @Test
    public void testGetCityStrArr() {
        String[] stringArray = accessCityCode.getCityStrArr();
        assertNotNull("first sequential course should not be null", stringArray[0]);
        assertTrue("Calgary YYC".equals(stringArray[0]));
        assertEquals(13, stringArray.length);
    }

    @Test
    public void testGetCityObjArr() {
        CityCode[] objArray = accessCityCode.getCityObjArr();
        assertNotNull("first sequential course should not be null", objArray[0]);
        assertTrue("YYC".equals(objArray[0].getCode()));
        assertTrue("Calgary".equals(objArray[0].getName()));
        assertEquals(13, objArray.length);
    }

//    @After
//    public void tearDown() {
//        // reset DB
//        this.tempDB.delete();
//    }
}
