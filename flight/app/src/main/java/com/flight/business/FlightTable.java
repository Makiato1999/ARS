package com.flight.business;

import java.util.ArrayList;
import com.flight.objects.Flight;
public class FlightTable {

    private final FlightsInfo flightinfo;
    private final int  size = 5;
    private static  ArrayList<FlightsInfo> flTable;

    //CONSTRUCTOR takes in flightinfo class to populate the table
    public FlightTable(FlightsInfo fi1){
        flightinfo = fi1;
        flTable = new ArrayList<FlightsInfo>();
        for(int i = 0 ; i < size; i++){
            FlightsInfo xx = new FlightsInfo(flightinfo.getFlight());
            flTable.add(xx);
        }
    }

    //FOR TEST PURPOSE ONLY PLEASE IGNORE
//     public static void main(String[] args){
//         Flight f = new Flight("YWG","YYC",1100);
//         FlightsInfo fi = new FlightsInfo(f);
//         FlightTable flt = new FlightTable(fi);
//         System.out.println("FLIGHT TABLE: \n" + flt );
//     }

    //returns the Flight table
    public ArrayList<FlightsInfo> getFlightTable(){
        return flTable;
    }

    //Print the FLight table
    public String toString(){
        String out = "";
        for(int i = 0; i < size; i++){
            out += flTable.get(i).toString() + "\n";
        }
        return out;
    }



}
