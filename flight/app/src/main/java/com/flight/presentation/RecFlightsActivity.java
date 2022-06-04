package com.flight.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.flight.R;
import com.flight.business.FlightTable;
import com.flight.business.SearchHandler;

public class RecFlightsActivity extends AppCompatActivity implements RecViewInterface{
    FlightTable flightTable;
    String travelDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_flights_activity);
        RecyclerView recyclerView = findViewById(R.id.flights_recycler_view_id);

        //Get the content filled on the main activity
        Intent intent = getIntent();
        String depCityString = intent.getStringExtra("departureCity");
        String arrCityString = intent.getStringExtra("arrivalCity");
        String dateString = intent.getStringExtra("travelDate");
        travelDate = dateString;
        String depCityCode = depCityString.substring(depCityString.length()-3);
        String arrCityCode = arrCityString.substring(arrCityString.length()-3);

        //<editor-fold defaultstate="collapsed" desc="For use if passing array to search handler ">
        /*
        String[] depCityArray = depCityString.split(",");
        String[] arrCityArray = arrCityString.split(",");
         */
        // </editor-fold>

        //Set up our flight information
//        SearchHandler searchHandler = new SearchHandler("YYC", "YVR" ,"jan30");
        SearchHandler searchHandler = new SearchHandler(depCityCode, arrCityCode, dateString);

        //The grader can chose between the 2 if they want to run the actual database or fake Databse.
        //Line 43 runs fake databse that is inside flightHandler
        //FlightTable flightTable = searchHandler.handleFakeDB();
        //Line 45 runs real database that is inside hsqldb folder

        flightTable = searchHandler.handleRealDB();

        //Set up adapter for the recycler view
//        RecFlightsAdapter adapter = new RecFlightsAdapter(this, flightTable);
        RecFlightsAdapter adapter = new RecFlightsAdapter(this, flightTable, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    //Will be used to create reservation form
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ReservationFormActivity.class); //create empty activity and its related form

        //WILL BE NEEDING ARRIVAL TIMES FROM LOGIC LAYER


        intent.putExtra("departureCity1", flightTable.getFlightTable().get(position).getDepCity());
        intent.putExtra("departureTime1", flightTable.getFlightTable().get(position).getDepartureTime().substring(0,5));
//        intent.putExtra("departureTime1", flightTable.getFlightTable().get(position).getDepCity());
        intent.putExtra("arrivalTime1", flightTable.getFlightTable().get(position).getArrivalTime().substring(0,5));
        intent.putExtra("arrivalCity1", flightTable.getFlightTable().get(position).getArrCity());

        intent.putExtra("departureCity2", flightTable.getFlightTable().get(position).getArrCity());
//        intent.putExtra("departureTime2", flightTable.getFlightTable().get(position).getArrivalTime().substring(0,5)); //to be added in iteration3
        //intent.putExtra("departureTime2", "8:20");
        intent.putExtra("departureTime2", flightTable.getFlightTable().get(position).getDepartureTime2().substring(0,5));

//        intent.putExtra("arrivalTime2", flightTable.getFlightTable().get(position).getArrivalTime2());
        intent.putExtra("arrivalCity2", flightTable.getFlightTable().get(position).getDepCity());

        String price1 = flightTable.getFlightTable().get(position).getStrPrice();
        intent.putExtra("Price", price1 );

        intent.putExtra("date", travelDate);

        startActivity(intent);
    }
}
