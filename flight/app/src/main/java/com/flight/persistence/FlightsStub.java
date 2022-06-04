package com.flight.persistence;

import com.flight.objects.Flight;

public class FlightsStub implements IHsqldbFlights{
    //Able to search fake object, will implement a real database soon

    private final int TEMPSIZE = 4;
    private Flight[] tempFlightsArray;

    public FlightsStub(){
        tempInitialize();
    }

    private void tempInitialize(){
        tempFlightsArray = new Flight[TEMPSIZE];

        tempFlightsArray[0] = new Flight("YWG","YYC",1191);
        tempFlightsArray[1] = new Flight("YYC","YVR",686);
        tempFlightsArray[2] = new Flight("YVR","YYZ",3345);
        tempFlightsArray[3] = new Flight("YYZ","YEG",2689);
    }

    public Flight search(String departureCityCode, String arrivalCityCode){
        Flight target = null;

        for(int i = 0; i < TEMPSIZE; i++){
            if(departureCityCode.equals(tempFlightsArray[i].getDepartureCityCode()) && arrivalCityCode.equals(tempFlightsArray[i].getArrivalCityCode())){
                target = tempFlightsArray[i];
            }
        }

        return target;
    }

    public void print(){
        for(int i = 0; i < TEMPSIZE; i++){
            tempFlightsArray[i].print();
        }
    }
}
