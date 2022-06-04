package com.flight.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flight.R;
import com.flight.business.FlightTable;

import java.util.ArrayList;

public class RecFlightsAdapter extends RecyclerView.Adapter<RecFlightsAdapter.MyViewHolder> {

    //Variable to hold recycler view interface
    private final RecViewInterface recViewInterface;// add this to my constructor

    Context context;
    FlightTable flightTable;

    public RecFlightsAdapter(Context context, FlightTable flightTable,
                             RecViewInterface recViewInterface) {
        this.context = context;
        this.flightTable = flightTable;
        this.recViewInterface = recViewInterface;
    }

    @NonNull
    @Override
    public RecFlightsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //This is where we inflate layout (giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_flight_row, parent, false);

        return new RecFlightsAdapter.MyViewHolder(view, recViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecFlightsAdapter.MyViewHolder holder, int position) {
        //Assigning values to our row as they come back on the screen
        //-- DETAILED: Assigning values to the views we created in the recycle_view_row layout file
        //              based on the position of the recycler view

        holder.departureTime1.setText(flightTable.getFlightTable().get(position).getDepartureTime().substring(0,5));
        holder.departureCity1.setText(flightTable.getFlightTable().get(position).getDepCity());
        holder.arrivalTime1.setText(flightTable.getFlightTable().get(position).getArrivalTime().substring(0,5));
        holder.arrivalCity1.setText(flightTable.getFlightTable().get(position).getArrCity());
        holder.duration1.setText(flightTable.getFlightTable().get(position).getDuration()); //to be added in iteration3

        holder.departureTime2.setText(flightTable.getFlightTable().get(position).getDepartureTime2().substring(0,5)); //to be added in iteration3
        holder.departureCity2.setText(flightTable.getFlightTable().get(position).getArrCity());
        holder.arrivalTime2.setText(flightTable.getFlightTable().get(position).getArrivalTime2().substring(0,5));
        holder.arrivalCity2.setText(flightTable.getFlightTable().get(position).getDepCity());
        holder.duration2.setText(flightTable.getFlightTable().get(position).getDuration());
//        holder.price.setText("C$ "+flightTable.getFlightTable().get(position).getPrice());
//      String price1 = "C$ " + flightTable.getFlightTable().get(position).getPrice();
        holder.price.setText(flightTable.getFlightTable().get(position).getStrPrice());


    }

    @Override
    public int getItemCount() {
        //Know how many items i have in total
        return flightTable.getFlightTable().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //Like our own onCreate method
        // Detailed: grabbing the views from our recycler view_row_layout file
        ImageView imageView;
        TextView departureTime1, departureCity1, arrivalTime1, arrivalCity1, duration1;
        TextView departureTime2, departureCity2, arrivalTime2, arrivalCity2, duration2;
        TextView price;


//        public MyViewHolder(@NonNull View itemView) {
        public MyViewHolder(@NonNull View itemView, RecViewInterface recViewInterface) {
            super(itemView);
            //set our stuff
            departureTime1 = itemView.findViewById(R.id.dep_time1_txt_id);
            departureCity1 = itemView.findViewById(R.id.dep_city1_txt_id);
            arrivalTime1 = itemView.findViewById(R.id.arrival_time1_txt_id);
            arrivalCity1 = itemView.findViewById(R.id.arrival_city1_txt_id);
            duration1 = itemView.findViewById(R.id.duration1_txt_id);

            departureTime2 = itemView.findViewById(R.id.dep_time2_txt_id);
            departureCity2 = itemView.findViewById(R.id.dep_city2_txt_id);
            arrivalTime2 = itemView.findViewById(R.id.arrival_time2_txt_id);
            arrivalCity2 = itemView.findViewById(R.id.arrival_city2_txt_id);
            duration2 = itemView.findViewById(R.id.duration2_txt_id);

            price = itemView.findViewById(R.id.price_txt_id);

            Button reserve = itemView.findViewById(R.id.button);

            //Adding an onclick listener on our item view: NOTE I WANT IT ON A BUTTON
            reserve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recViewInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){//Step 6.2 ensure position is valid
                            recViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}
