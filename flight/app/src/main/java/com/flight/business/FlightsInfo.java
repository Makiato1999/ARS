package com.flight.business;


import com.flight.objects.Flight;

import java.sql.Time;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.util.Random;


public class  FlightsInfo{

    private String arrivalCityCode = null;
    private String departureCityCode = null;
    private int distance = 0;
    private Flight flight = null;
    private Time[] fTime;
    private Time[] fTime2;
    private long dur;
    private double fPrice;


    //CONSTRUCTOR FlightInfo takes in Flight as constructor
    public FlightsInfo(Flight f1) {
        try{
            arrivalCityCode = f1.getArrivalCityCode();
            departureCityCode = f1.getDepartureCityCode();
            distance = f1.getDistance();
            flight = f1;
            fTime = calDepArrTime();
            fTime2 = calDepArrTime();
            fPrice = calPrice();
        }catch (Exception e){
            System.out.println("City code does not exist in Database, Exception:" +e);
            throw e;
        }
    }

    //FOR TEST PURPOSE ONLY PLEASE IGNORE
//     public static void main(String[] args){
//         Flight f = new Flight("YWG","YYC",1400);
//         FlightsInfo fi = new FlightsInfo(f);
//
//         System.out.println(fi);
//
//     }

    //returns price of fight according to distance
    private double calPrice() {
        //check distance and based on that assign price
        double price = 0;


        Random rand = new Random();
        if (distance >= 0 && distance < 500) {
            price = 100 + rand.nextInt(10000) / 100.0;
        } else if (distance >= 500 && distance < 1000) {
            price = 200 + rand.nextInt(10000) / 100.0;
        } else if (distance >= 1000 && distance < 1500) {
            price = 300 + rand.nextInt(10000) / 100.0;
        } else if (distance >= 1500 && distance < 2000) {
            price = 400 + rand.nextInt(10000) / 100.0;
        } else if (distance >= 2000 && distance < 2500) {
            price = 500 + rand.nextInt(10000) / 100.0;
        } else if (distance >= 2500 && distance < 3000) {
            price = 600 + rand.nextInt(10000) / 100.0;
        } else if (distance >= 3000 && distance < 3500) {
            price = 700 + rand.nextInt(10000) / 100.0;
        } else if (distance >= 3500 && distance < 4000) {
            price = 800 + rand.nextInt(10000) / 100.0;
        } else if (distance >= 4000 && distance < 4500) {
            price = 900 + rand.nextInt(10000) / 100.0;
        } else if (distance >= 4500 && distance < 5000) {
            price = 1000 + rand.nextInt(10000) / 100.0;
        }
        //formatting price to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        price = Double.parseDouble(df.format(price));
        return price;
    }

    //returns Time[] where
    // Time[0] -> Departure time
    // Time[1] -> Arrival time
    private Time[] calDepArrTime() {

        Time[] tt = new Time[2];
        // Assign a random time and stick with it
        Random rand = new Random();

        //creating random time within 24 hours range
        final int millisInDay = 24*60*60*1000;

        Time t0 = new Time((long)rand.nextInt(millisInDay));
        tt[0] = t0;
        //System.out.println(t0);
        dur = (long)(60D * ((double) distance /500));
        Time t1 = new Time(t0.getTime() + (long)(3600000D * ((double) distance /500)));
        //System.out.println(t1 + " "+ t0 + " Duration " + (num/60L) +":" + (num % 60L));
        tt[1] = t1;
        return tt;
    }
    //returns price of this specific FLight
    public double getPrice(){
        return fPrice;
    }
    //returns price of the flight in a String Format
    //had issues with app crashing so implemented an extra function
    public String getStrPrice(){
        return "C$ " + fPrice;
    }
    //returns departure time as String
    public String getDepartureTime(){
        return fTime[0].toString();
    }
    //returns Arrival time as string
    public String getArrivalTime(){
        return fTime[1].toString();
    }

    //returns Departure time for return flight
    public String getDepartureTime2(){return fTime2[0].toString();}

    //return Arrival time for return Flight
    public String getArrivalTime2() {return fTime2[1].toString();}

    //returns durations of the flight
    public String getDuration(){
        return (dur/60L) +":" + (dur % 60L);
    }

    //returns Departure City code
    public String getDepCity(){ return departureCityCode ;}

    //returns arrival city code
    public String getArrCity(){
        return arrivalCityCode;
    }

    //returns distance between cities
    public int getDistance(){
        return distance;
    }

    //returns Flight object that is passed  in flight info constructor
    public Flight getFlight(){
        return flight;
    }

    //Simple print function to print flight info properly.
    public String toString(){
        return getDepCity() +" "+ getArrCity() +"| DEP time "+ getDepartureTime() +"| ARR time "+ getArrivalTime() +"| Price:"+ getPrice() + "| Duration: " + getDuration() + "||" ;
    }
}
