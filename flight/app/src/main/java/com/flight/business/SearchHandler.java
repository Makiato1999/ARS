package com.flight.business;
import com.flight.application.Services;
import com.flight.objects.Flight;
import com.flight.persistence.IHsqldbFlights;

public class SearchHandler {
    private IHsqldbFlights ihsqldbFlights;
    private String arrivalCity;
    private String departureCity;
    private String date;
    private FlightTable table;
    public SearchHandler(String[] departure, String[] arrival, String d){

        try{
            departureCity = departure[1];
            arrivalCity = arrival[1];
            date = d;
        } catch (Exception e){
            System.out.println("Possibly nothing in str[1] for departure or arrival city:" +e);
        }
        ihsqldbFlights = Services.getFlightPersistence();
    }

    public SearchHandler(String[] departure, String[] arrival, String d, final IHsqldbFlights ihsqldbFlights){
        this(departure,arrival,d);
        this.ihsqldbFlights = ihsqldbFlights;

    }

    //***************Added to account for only the departure and city code *************************
    public SearchHandler(String departure, String arrival, String d){
        try{
            departureCity = departure;
            arrivalCity = arrival;
            date = d;
        } catch (Exception e){
            System.out.println("Possibly nothing in str[1] for departure or arrival city:" +e);
        }
        ihsqldbFlights = Services.getFlightPersistence();
    }
    public SearchHandler(String departure, String arrival, String d, final IHsqldbFlights ihsqldbFlights){
        this(departure,arrival,d);
        this.ihsqldbFlights = ihsqldbFlights;
    }
    //**********************************************************************************************

    //PLease ignore for test purpose only.
//    public static void main(String[] args){
//        SearchHandler ss = new SearchHandler(new String[]{"Winnipeg", "YWG"}, new String[]{"Calgary","YYC"} ,"jan30");
//
//        //ss.populateFakeDB();
//
//        FlightTable flt = ss.handleFakeDB();
//        System.out.println("FLIGHT TABLE: "+ ss.getDate()+"\n" + flt );
//    }

    //implementing fake database for the purpose of testing
    private void populateFakeDB(){
        IHsqldbFlights fhfk = Services.getFlightHandler();
        Flight f = fhfk.search(departureCity,arrivalCity);
        FlightsInfo fi = new FlightsInfo(f);
        table = new FlightTable(fi);
    }

    private void populateRealDB(){

        Flight x = ihsqldbFlights.search(departureCity,arrivalCity);
        FlightsInfo ii = new FlightsInfo(x);
        table = new FlightTable(ii);

    }

    public String getDate(){
        return date;
    }

    public FlightTable handleRealDB(){
        table = null;
        populateRealDB();
        return table;
    }

    public FlightTable handleFakeDB(){
        table = null;
        populateFakeDB();
        return table;
    }
}
