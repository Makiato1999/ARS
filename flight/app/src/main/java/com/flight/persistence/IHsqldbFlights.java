package com.flight.persistence;

import com.flight.objects.Flight;

public interface IHsqldbFlights {
    public Flight search(String departureCityCode, String arrivalCityCode);
}
