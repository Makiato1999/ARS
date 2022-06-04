package com.flight.presentation;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.flight.R;
import com.flight.application.Main;
import com.flight.business.AccessCityCode;
import com.flight.business.ResSearchHandler;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //variables
    EditText select_dates, adult_num, children_num, arrival_city, departure_city;
    Switch select_trip_type;
    TextView travellersOptions;
    CheckBox adult_checkbox, children_checkbox;
    // search city code variables
    private Dialog dialog;
    private boolean isDeparture = true;
    private AccessCityCode accessCityCode;
    String[] cityArray;
    private SearchView searchCityCode_SearchView;
    private ListView searchCityCode_ListView;
    private String userChoice_departureCityCode; // result
    private String userChoice_arrivalCityCode;   // result

    //View Reservations variables
    TextView email, resInfo;
    String emailString;
    Dialog loginDialog, resInfoDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        copyDatabaseToDevice();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Enter full screen view here
        super.onCreate(savedInstanceState);
        setTheme(R.style.Base_Theme_AppCompat_Light_DarkActionBar); //CREATING A DARK THEME

        getSupportActionBar().hide(); //hiding the action bar
        setContentView(R.layout.activity_main);


        //Default value
        adult_num = findViewById(R.id.adult_num_edit_id);
        adult_num.setText("1");
        children_num = findViewById(R.id.children_num_edit_id);
        children_num.setEnabled(false);

        //Calling functions for each associated traveller enters
        setDate();
        departureCity();
        arrivalCity();

    }

    //Add select Trip type here
    public void selectTripType(View view){
        select_trip_type = findViewById(R.id.select_trip_type_id);
        select_dates = findViewById(R.id.select_dates_edit);
        if(select_trip_type.isChecked()){
            select_trip_type.setText("Round trip");
            select_dates.setText("Select Round Trip Dates ");
        }else {
            select_trip_type.setText("One way");
            select_dates.setText("Select One way Trip Dates");
        }
    }

    //Add Select date feature here
    public void setDate(){
        select_dates = findViewById(R.id.select_dates_edit);
        //Constraint to have only dates from today onwards can be selected
        CalendarConstraints.Builder today = new CalendarConstraints.Builder();
        today.setValidator(DateValidatorPointForward.now());

        //Creating date range for round trips
        MaterialDatePicker.Builder roundTripBuilder = MaterialDatePicker.Builder.dateRangePicker();
        roundTripBuilder.setTitleText("Travelling dates");
        roundTripBuilder.setCalendarConstraints(today.build());
        final MaterialDatePicker dateRange = roundTripBuilder.build();

        //Creating date picker for one way
        MaterialDatePicker.Builder oneWayTripBuilder = MaterialDatePicker.Builder.datePicker();
        oneWayTripBuilder.setTitleText("Travelling dates");
        oneWayTripBuilder.setCalendarConstraints(today.build());
        final MaterialDatePicker datePicker = oneWayTripBuilder.build();

        //Adding onclick to open the popup
        select_dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_trip_type = findViewById(R.id.select_trip_type_id);
                travellersOptions = findViewById(R.id.travellers_options_id);

                if(select_trip_type.isChecked()){
                    dateRange.show(getSupportFragmentManager(), "Material Range");
                    dateRange.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            select_dates.setText(dateRange.getHeaderText());
                        }

                    });
                }else {
                    datePicker.show(getSupportFragmentManager(), "Date_Picker");
                    datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            select_dates.setText(datePicker.getHeaderText());
                        }
                    });
                }
            }
        });
    }

    // departureCity feature
    public void departureCity(){
        // find departure_city TextView
        departure_city = findViewById(R.id.departure_city_edit_id);
        // listen user click TextView
        departure_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this is departure
                isDeparture = true;
                showDialog();
            }
        });
    }

    // arrivalCity feature
    public void arrivalCity(){
        // find arrival_city TextView
        arrival_city = findViewById(R.id.arrival_city_edit_id);
        // listen user click TextView
        arrival_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this is arrival
                isDeparture = false;
                showDialog();
            }
        });
    }

    // show popup dialog
    public void showDialog(){
        // initialize dialog
        dialog = new Dialog(MainActivity.this, R.style.SearchCityCodeActivityTheme);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View searchCityCode_dialog = layoutInflater.inflate(R.layout.activity_search_citycode, null);
        // find SearchView and ListView
        searchCityCode_SearchView = (SearchView) searchCityCode_dialog.findViewById(R.id.CityCode_searchview);
        searchCityCode_ListView = (ListView) searchCityCode_dialog.findViewById(R.id.CityCode_listview);
        // initialize logic and database
        accessCityCode = new AccessCityCode();
        cityArray = accessCityCode.getCityStrArr();
        // initialize SearchView and ListView
        set_searchCityCode_ListView(cityArray, isDeparture);
        set_searchCityCode_SearchView(cityArray, isDeparture);
        // set dialog layout
        dialog.setContentView(searchCityCode_dialog);
        // set dialog title
        dialog.setTitle("Search City Code");
        // show
        dialog.show();
    }

    // set ListView
    public void set_searchCityCode_ListView(String[] cityArray, boolean isDeparture){
        // initialize adapter
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cityArray);
        searchCityCode_ListView.setAdapter(adapter);
        // initialize ListView filter
        searchCityCode_ListView.setTextFilterEnabled(true);
        // listener
        searchCityCode_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>parent, View view, int position, long id) {
                // is departureCity / arrivalCity
                if (isDeparture) {
                    // get user clicked result
                    userChoice_departureCityCode = ((TextView)view).getText().toString();
                    // update SearchView hint
                    searchCityCode_SearchView.setQueryHint(userChoice_departureCityCode);
                    // prompt
                    Toast.makeText(getApplicationContext(),
                            "Departure city is: " + userChoice_departureCityCode,
                            Toast.LENGTH_SHORT).show();
                    // update TextView
                    departure_city.setText(userChoice_departureCityCode);
                } else {
                    // get user clicked result
                    userChoice_arrivalCityCode = ((TextView)view).getText().toString();
                    // update SearchView hint
                    searchCityCode_SearchView.setQueryHint(userChoice_arrivalCityCode);
                    // prompt
                    Toast.makeText(getApplicationContext(),
                            "Arrival city is: " + userChoice_arrivalCityCode,
                            Toast.LENGTH_SHORT).show();
                    // update TextView
                    arrival_city.setText(userChoice_arrivalCityCode);
                }
                dialog.dismiss();
            }
        });
    }

    // set SearchView
    public void set_searchCityCode_SearchView(String[] cityArray, boolean isDeparture){
        // initialize
        searchCityCode_SearchView.setIconifiedByDefault(false);
        // add search icon (submit button)
        searchCityCode_SearchView.setSubmitButtonEnabled(true);
        // SearchView hint
        // is departureCity / arrivalCity
        if (isDeparture) {
            searchCityCode_SearchView.setQueryHint("Please input Departure city/code");
        } else {
            searchCityCode_SearchView.setQueryHint("Please input Arrival city/code");
        }
        // listener
        searchCityCode_SearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // query is user input which is submitted
                // search in business layer
                if (accessCityCode.isInputValid(query)) {
                    if (accessCityCode.isFindCity(query)) {
                        // is departureCity / arrivalCity
                        if (isDeparture) {
                            Toast.makeText(getApplicationContext(),
                                    "Departure city is: " + accessCityCode.getFindCityStr(query),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Arrival city is: " + accessCityCode.getFindCityStr(query),
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Sorry we can't find your input city/code: " + query,
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Invalid input!",
                            Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    // clean filter
                    searchCityCode_ListView.clearTextFilter();
                } else {
                    // filter ViewList by user input
                    searchCityCode_ListView.setFilterText(newText);
                }
                return true;
            }
        });
    }

    public void adultTravelOption(View view){
        adult_checkbox = findViewById(R.id.adult_checkbox_id);
        adult_num = findViewById(R.id.adult_num_edit_id);

        if(adult_checkbox.isChecked()){
            adult_num.setText("1");
            adult_num.setEnabled(true);
        }else{
            adult_num.setText("");
            adult_num.setEnabled(false);
        }
    }

    public void childrenTravelOption(View view){
        children_checkbox = findViewById(R.id.child_checkbox_id);
        children_num = findViewById(R.id.children_num_edit_id);

        if(children_checkbox.isChecked()){
            children_num.setText("1");
            children_num.setEnabled(true);
        }else{
            children_num.setText("");
            children_num.setEnabled(false);
        }
    }

    //Opens the search results in a recycler view
    public void searchFlights(View view){ //this will be called directly on the button
        Intent intent = new Intent(this, RecFlightsActivity.class);

        //Passing the cities to search activity
        departure_city = findViewById(R.id.departure_city_edit_id);
        String depCityString = departure_city.getText().toString();
        arrival_city = findViewById(R.id.arrival_city_edit_id);
        String arrCityString = arrival_city.getText().toString();
        select_dates = findViewById(R.id.select_dates_edit);
        String dateString = select_dates.getText().toString();
        adult_num = findViewById(R.id.adult_num_edit_id);
        String adultNumString = adult_num.getText().toString();
        children_num = findViewById(R.id.children_num_edit_id);
        String childrenNUmString = children_num.getText().toString();

//        travellersOptions = findViewById(R.id.travellers_options_id);
        //Ensuring Form is filled
        if(depCityString.toLowerCase().contains("flying") ||
                arrCityString.toLowerCase().contains("flying") ||
                dateString.toLowerCase().contains("dates")){
            if(depCityString.toLowerCase().contains("flying")){
                departure_city.setError("Departure City is required!");
                departure_city.requestFocus();
            }
            if (arrCityString.toLowerCase().contains("flying")){
                arrival_city.setError("Arrival City is required!");
                arrival_city.requestFocus();
            }
            if(dateString.toLowerCase().contains("dates")){
                select_dates.setError("Travel date is required");
            }
        }else{
            intent.putExtra("departureCity", depCityString);
            intent.putExtra("arrivalCity", arrCityString);
            intent.putExtra("travelDate", dateString);
            intent.putExtra("adultNum", adultNumString);
            intent.putExtra("childrenNum", childrenNUmString);
            startActivity(intent);
        }
    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch (final IOException ioe) {
            //Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }


    //Open loginDialog
    public void openLoginDialog(View view){
        loginDialog = new Dialog(this);
        loginDialog.setContentView(R.layout.view_res_login_dialog);
        loginDialog.show();
    }

    public void dismissLogin(View view){
        loginDialog.dismiss();
    }

    //Show reservation info
    public void showUserReservations(View view){
        email = loginDialog.findViewById(R.id.email_login_txt_id);
        emailString = email.getText().toString();
        if(!emailString.contains("@")) {
            email.setError("Enter valid email");
        }else{
            resInfoDialog = new Dialog(this);
            resInfoDialog.setContentView(R.layout.view_res_info);
            resInfo = resInfoDialog.findViewById(R.id.res_info_id);

            ResSearchHandler resSearchHandler = new ResSearchHandler(emailString);
//            resSearchHandler.getResTable();
//            resInfo.append("RUNNING BEFORE ");
            //Set up reservation info
            if(resSearchHandler.emailExist() == true){
                for(int i=0; i<resSearchHandler.getResTable().size(); i++){
//                resInfo.append("RUNNING IN FOR LOOP ");
                    resInfo.append("\nReservation Info for: "+ resSearchHandler.getResTable().get(i).getDate());
                    resInfo.append("\n\t\t\t    Departing Time: "+ resSearchHandler.getResTable().get(i).getdepTime());
                    resInfo.append("\n\t\t\t    Departing City: "+ resSearchHandler.getResTable().get(i).getDepart());
                    resInfo.append("\n\t\t\t    Returning Time: "+ resSearchHandler.getResTable().get(i).getdepTime2());
                    resInfo.append("\n\t\t\t    Returning City: "+ resSearchHandler.getResTable().get(i).getDepart2());
                    resInfo.append("\n\t\t\t    Reservation Total Price: "+ resSearchHandler.getResTable().get(i).getPrice());
                    resInfo.append("\n\n   ");
                }
                //display reservation info to the user
                resInfoDialog.show();
            } else {
                resInfo.append("\nEmail Not found! \n\t Please Try Again!");
            }

        }
    }


    public void dismissResInfo(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}