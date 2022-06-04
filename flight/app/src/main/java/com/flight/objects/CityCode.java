package com.flight.objects;

//just and object to hold a city name and code pair
public class CityCode {

    private String cityName;
    private String code;

    public CityCode() {
        //empty constructor
    }

    public CityCode(String name, String c) {
        cityName = name;
        code = c;
    }

    public String getName() {
        return cityName;
    }

    public String getCode() {
        return code;
    }

    public void print() {
        System.out.println("("+cityName+" , "+code+")");
    }
}

