package com.flight.business;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import com.flight.objects.City;
import com.flight.objects.CityCode;


public class TestSearchCityCode {
    private String query;
    private AccessCityCode searchLogic;
    private CityCode[] fkDB;

    @BeforeClass
    public static void Test_starts() {
        System.out.println("Starting test TestSearchCityCode\n");
    }

    @Before
    public void setUp() {
        searchLogic = new AccessCityCode(true);
    }

    @Test
    public void Test_isInputInvalid_empty() {
        System.out.println("Starting Test_isInputInvalid_empty\n");
        query = "";
        assertFalse(searchLogic.isInputValid(query));
        System.out.println("Finished Test_isInputInvalid_empty\n");
    }
    @Test
    public void Test_isInputInvalid_onlySpace() {
        System.out.println("Starting Test_isInputInvalid_onlySpace\n");
        query = " ";
        assertFalse(searchLogic.isInputValid(query));
        query = "    ";
        assertFalse(searchLogic.isInputValid(query));
        System.out.println("Finished Test_isInputInvalid_onlySpace\n");
    }
    @Test
    public void Test_isInputInvalid_correct() {
        System.out.println("Starting Test_isInputInvalid_correct\n");
        query = "Winnipeg";
        assertTrue(searchLogic.isInputValid(query));
        query = "WINNIPEG";
        assertTrue(searchLogic.isInputValid(query));
        query = "Winnipeg12233";
        assertTrue(searchLogic.isInputValid(query));
        System.out.println("Finished Test_isInputInvalid_correct\n");
    }
    @Test
    public void Test_isInputInvalid_spaceBetweenQuery() {
        System.out.println("Starting Test_isInputInvalid_spaceBetweenQuery\n");
        query = "Winni peg";
        assertFalse(searchLogic.isInputValid(query));
        query = "Win nip eg";
        assertFalse(searchLogic.isInputValid(query));
        query = "Winnipe     g";
        assertFalse(searchLogic.isInputValid(query));
        System.out.println("Finished Test_isInputInvalid_spaceBetweenQuery\n");
    }
    @Test
    public void Test_isInputInvalid_spaceBeforeQuery() {
        System.out.println("Starting Test_isInputInvalid_spaceBeforeQuery\n");
        query = " Winnipeg";
        assertFalse(searchLogic.isInputValid(query));
        query = "     Winnipeg";
        assertFalse(searchLogic.isInputValid(query));
        query = "  Winnip eg";
        assertFalse(searchLogic.isInputValid(query));
        query = " Winnipe     g";
        assertFalse(searchLogic.isInputValid(query));
        System.out.println("Finished Test_isInputInvalid_spaceBeforeQuery\n");
    }
    @Test
    public void Test_isInputInvalid_spaceAfterQuery() {
        System.out.println("Starting Test_isInputInvalid_spaceAfterQuery\n");
        query = "Winnipeg ";
        assertFalse(searchLogic.isInputValid(query));
        query = "Winnipeg       ";
        assertFalse(searchLogic.isInputValid(query));
        query = "Winnip eg   ";
        assertFalse(searchLogic.isInputValid(query));
        query = "Winnipe     g    ";
        assertFalse(searchLogic.isInputValid(query));
        System.out.println("Finished Test_isInputInvalid_spaceAfterQuery\n");
    }
    @Test
    public void Test_isInputInvalid_spaceMixedQuery() {
        System.out.println("Starting Test_isInputInvalid_spaceMixedQuery\n");
        query = "  Winnipeg ";
        assertFalse(searchLogic.isInputValid(query));
        query = "Winni  peg       ";
        assertFalse(searchLogic.isInputValid(query));
        query = "       Winnip eg";
        assertFalse(searchLogic.isInputValid(query));
        query = "     Winn  ipe     g    ";
        assertFalse(searchLogic.isInputValid(query));
        System.out.println("Finished Test_isInputInvalid_spaceMixedQuery\n");
    }

    // test if return correct boolean and test if can ignore user query case
    @Test
    public void Test_isFind_normalCase() {
        System.out.println("Starting Test_isFind_normalCase\n");
        boolean result1 = searchLogic.isFindCity("Calgary");
        assertTrue(result1);
        boolean result2 = searchLogic.isFindCity("YYC");
        assertTrue(result2);
        System.out.println("Finished Test_isFind_normalCase\n");
    }
    @Test
    public void Test_isFind_lowerCase() {
        System.out.println("Starting Test_isFind_lowerCase\n");
        boolean result1 = searchLogic.isFindCity("calgary");
        assertTrue(result1);
        boolean result2 = searchLogic.isFindCity("yyc");
        assertTrue(result2);
        System.out.println("Finished Test_isFind_lowerCase\n");
    }
    @Test
    public void Test_isFind_upperCase() {
        System.out.println("Starting Test_isFind_upperCase\n");
        boolean result1 = searchLogic.isFindCity("CALGARY");
        assertTrue(result1);
        boolean result2 = searchLogic.isFindCity("YYC");
        assertTrue(result2);
        System.out.println("Finished Test_isFind_upperCase\n");
    }
    @Test
    public void Test_isFind_casualCase() {
        System.out.println("Starting Test_isFind_casualCase\n");
        boolean result1 = searchLogic.isFindCity("CalGArY");
        assertTrue(result1);
        boolean result2 = searchLogic.isFindCity("YyC");
        assertTrue(result2);
        System.out.println("Finished Test_isFind_casualCase\n");
    }
    @Test
    public void Test_notFind_normalCase() {
        System.out.println("Starting Test_notFind_normalCase\n");
        boolean result1 = searchLogic.isFindCity("Calgar");
        assertFalse(result1);
        boolean result2 = searchLogic.isFindCity("YY");
        assertFalse(result2);
        System.out.println("Finished Test_notFind_normalCase\n");
    }
    @Test
    public void Test_notFind_lowerCase() {
        System.out.println("Starting Test_notFind_lowerCase\n");
        boolean result1 = searchLogic.isFindCity("calgry");
        assertFalse(result1);
        boolean result2 = searchLogic.isFindCity("yc");
        assertFalse(result2);
        System.out.println("Finished Test_notFind_lowerCase\n");
    }
    @Test
    public void Test_notFind_upperCase() {
        System.out.println("Starting Test_notFind_upperCase\n");
        boolean result1 = searchLogic.isFindCity("CAGARY");
        assertFalse(result1);
        boolean result2 = searchLogic.isFindCity("YC");
        assertFalse(result2);
        System.out.println("Finished Test_notFind_upperCase\n");
    }
    @Test
    public void Test_notFind_casualCase() {
        System.out.println("Starting Test_notFind_casualCase\n");
        boolean result1 = searchLogic.isFindCity("ClGAY");
        assertFalse(result1);
        boolean result2 = searchLogic.isFindCity("yC");
        assertFalse(result2);
        System.out.println("Finished Test_notFind_casualCase\n");
    }

    // test if return correct city object and if can ignore user query case
    @Test
    public void Test_getFindCityObj() {
        System.out.println("Starting Test_getFindCityObj\n");
        String query1 = "Winnipeg";
        CityCode result1 = searchLogic.getFindCityObj(query1);
        assertEquals(result1.getName(), query1);
        String query2 = "YWG";
        CityCode result2 = searchLogic.getFindCityObj(query2);
        assertEquals(result2.getCode(), query2);
        System.out.println("Finished Test_getFindCityObj\n");
    }
    @Test
    public void Test_getFindCityName_lowerCase() {
        System.out.println("Starting Test_getFindCityName_lowerCase\n");
        String query1 = "winnipeg";
        CityCode result1 = searchLogic.getFindCityObj(query1);
        assertNotNull(result1);
        String query2 = "ywg";
        CityCode result2 = searchLogic.getFindCityObj(query2);
        assertNotNull(result2);
        System.out.println("Finished Test_getFindCityName_lowerCase\n");
    }
    @Test
    public void Test_getFindCityName_upperCase() {
        System.out.println("Starting Test_getFindCityName_upperCase\n");
        String query = "WINNIPEG";
        CityCode result = searchLogic.getFindCityObj(query);
        assertNotNull(result);
        String query2 = "YWG";
        CityCode result2 = searchLogic.getFindCityObj(query2);
        assertNotNull(result2);
        System.out.println("Finished Test_getFindCityName_upperCase\n");
    }
    @Test
    public void Test_getFindCityName_casualCase() {
        System.out.println("Starting Test_getFindCityName_casualCase\n");
        String query = "WinNIpEG";
        CityCode result = searchLogic.getFindCityObj(query);
        assertNotNull(result);
        String query2 = "Ywg";
        CityCode result2 = searchLogic.getFindCityObj(query2);
        assertNotNull(result2);
        System.out.println("Finished Test_getFindCityName_casualCase\n");
    }

    // create a temp fake database to test
    public void create_fkDB() {
        fkDB = new CityCode[5];
        fkDB[0] = new CityCode("Calgary","YYC");
        fkDB[1] = new CityCode("Edmonton","YEG");
        fkDB[2] = new CityCode("Fredericton","YFC");
        fkDB[3] = new CityCode("Gander","YQX");
        fkDB[4] = new CityCode("Halifax","YHZ");
    }
    // test entire return array
    @Test
    public void Test_getCityObjArr() {
        System.out.println("Starting Test_getCityObjArr\n");
        create_fkDB();
        searchLogic = new AccessCityCode(fkDB);
        CityCode[] testArray = searchLogic.getCityObjArr();
        // 1. test two arrays whether have same length
        assertEquals(fkDB.length, testArray.length);
        // 2. test each item in array among two arrays
        for (int i = 0; i < fkDB.length; i++) {
            City city = new City(fkDB[i]);
            assertEquals(city.getCityName(), testArray[i].getName());
            assertEquals(city.getCityCode(), testArray[i].getCode());
        }
        System.out.println("Finished Test_getCityObjArr\n");
    }
    @Test
    public void Test_getCityStrArr() {
        System.out.println("Starting Test_getCityStrArr\n");
        create_fkDB();
        searchLogic = new AccessCityCode(fkDB);
        String[] testArray = searchLogic.getCityStrArr();
        // 1. test two arrays whether have same length
        assertEquals(fkDB.length, testArray.length);
        // 2. test each item in array among two arrays
        String temp = "";
        for (int i = 0; i < fkDB.length; i++) {
            City city = new City(fkDB[i]);
            assertEquals(city.toString(), testArray[i]);
        }
        System.out.println("Finished Test_getCityStrArr\n");
    }

    @AfterClass
    public static void Test_finish() {
        System.out.println("Finished test TestSearchCityCode\n");
    }
}
