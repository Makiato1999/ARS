package com.flight.objects;

public class Flight {
    //just a class to hold all info pertaining to a flight

    private String arrivalCityCode;
    private String departureCityCode;
    private int distance;

    public Flight(){
        //do nothing
    }

    public Flight(String dcc, String acc, int d){
        arrivalCityCode = acc;
        departureCityCode = dcc;
        distance = d;
    }

    public String getArrivalCityCode(){
        return arrivalCityCode;
    }

    public String getDepartureCityCode(){
        return departureCityCode;
    }

    public int getDistance(){
        return distance;
    }

    public void print(){
        System.out.println(departureCityCode+","+arrivalCityCode+","+distance);
    }
}
