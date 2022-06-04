package com.flight.objects;

public class City {
    private final String cityName;
    private final String cityCode;
    private final CityCode city;

    public City(final CityCode city) {
        this.cityName = city.getName();
        this.cityCode = city.getCode();
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public CityCode getCityObj() {
        return city;
    }

    public String toString() {
        return cityName + " " + cityCode;
    }
}
