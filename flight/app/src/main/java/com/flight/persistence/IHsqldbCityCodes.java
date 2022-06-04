package com.flight.persistence;

import com.flight.objects.CityCode;

public interface IHsqldbCityCodes {
    public CityCode search(String name);
    public void print();
    public CityCode[] getArray();
}