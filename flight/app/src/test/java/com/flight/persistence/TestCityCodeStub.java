package com.flight.persistence;

import org.junit.Test;

import static org.junit.Assert.*;

import com.flight.objects.CityCode;

class TestCityCodeStub {

    private CityCodesStub x = new CityCodesStub();
    private CityCode y;

    @Test
    void testSearch() {
        y = x.search("Winnipeg");


        assertEquals(y.getName(),"Winnipeg");
        assertEquals(y.getCode(),"YWG");
    }

    @Test
    void testNull() {
        y = x.search("Yeet");

        assertNull(y);
    }

    @Test
    void testSearchByCode() {
        y = x.search("YEG");

        assertEquals(y.getName(),"Edmonton");
        assertEquals(y.getCode(),"YEG");
    }

}
