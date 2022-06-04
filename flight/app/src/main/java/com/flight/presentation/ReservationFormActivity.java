package com.flight.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.flight.R;
import com.flight.business.ResInfo;
import com.flight.business.SearchHandler;
import com.flight.objects.Reservation;

public class ReservationFormActivity extends AppCompatActivity {

    //Form content
    TextView departureTime, departureCity, returnTime, returnCity, tripPrice;
    EditText email, name;

    //Dialog content
    TextView dialogMsg;
    Button dialogBttn;

    String traveldate, departureTimeStr, departureCityStr, returnTimeStr, returnCityStr, tripPriceStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_form);

        String departureCity1 = getIntent().getStringExtra("departureCity1");
        String departureTime1 = getIntent().getStringExtra("departureTime1");
        String arrivalTime1 = getIntent().getStringExtra("arrivalTime1");
        String arrivalCity1 = getIntent().getStringExtra("arrivalCity1");

        String departureCity2 = getIntent().getStringExtra("departureCity2");
        String departureTime2 = getIntent().getStringExtra("departureTime2");
        String arrivalTime2 = getIntent().getStringExtra("arrivalTime2");
        String arrivalCity2 = getIntent().getStringExtra("arrivalCity2");

        traveldate = getIntent().getStringExtra("date");

        String price = getIntent().getStringExtra("Price");


        TextView flightInfo = findViewById(R.id.flight_info_txtview_id);
//        flightInfo.append( " Date user selected");
        flightInfo.append( " "+traveldate);

        departureTime = findViewById(R.id.dep_time_txt_id);
        departureCity = findViewById(R.id.dep_city_txt_id);
        returnTime = findViewById(R.id.return_time_txt_id);
        returnCity = findViewById(R.id.return_city_txt_id);
        tripPrice = findViewById(R.id.form_price_txt_id);
        email = findViewById(R.id.form_email_edit_id);
        name = findViewById(R.id.form_name_edit_id);


        departureTime.setText(departureTime1);
        departureTimeStr = departureTime.getText().toString();

        departureCity.setText(departureCity1);
        departureCityStr = departureCity.getText().toString();

        returnTime.setText(departureTime2);
        returnTimeStr = returnTime.getText().toString();

        returnCity.setText(arrivalCity1);
        returnCityStr = returnCity.getText().toString();

        tripPrice.setText(price);
        tripPriceStr = tripPrice.getText().toString();

    }

    public void saveForm(View view) { //this will be called directly on the button

        String emailString = email.getText().toString();
        String nameString = name.getText().toString();

        //can definitely do more validations here including name as well
        if(!emailString.contains("@")){
            email.setError("Enter valid email");
        }else{
            //will be sending the travel date, departureTime, departureCity, returnTime, returnCity, tripPrice, email to database
            ResInfo resInfo = new ResInfo(emailString, traveldate, departureTimeStr,
                    departureCityStr, returnTimeStr, returnCityStr, tripPriceStr);
            resInfo.addResInfo();

            //Showing user a dialog saying they can view there information on the home page once saved
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.reservation_dialog);
            dialog.show();

        }
    }

    public void openMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}