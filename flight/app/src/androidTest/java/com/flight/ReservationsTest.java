package com.flight;

import android.util.Log;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.flight.application.Services;
import com.flight.objects.Reservation;
import com.flight.persistence.IHsqldbReservations;
import com.flight.presentation.MainActivity;

import org.junit.Rule;
import org.junit.Test;

public class ReservationsTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void insertAndRetrieve(){
        IHsqldbReservations x = Services.getReservationPersistence();
        Reservation y = new Reservation("jordon@hotmail.com","05/04/22","5:00","Winnipeg","6:00","Calgary","400.15");
        Reservation[] z;
        x.insert(y);
        z = x.search("jordon@hotmail.com");
        Log.d("DATE", z[0].getDate());
        assert(checkResArray(z,"jordon@hotmail.com","05/04/22","5:00","Winnipeg","6:00","Calgary","400.15"));
    }

    @Test
    public void notThere(){
        IHsqldbReservations x = Services.getReservationPersistence();
        Reservation[] z;
        z = x.search("yeet");
        assert(z.length == 0);
    }

    private boolean checkResArray(Reservation[] target,String email, String date, String dt, String d, String dtTwo, String dTwo, String p){
        boolean found = false;
        int i = 0;

        Log.d("DATE", email+date+dt+d+dtTwo+dTwo+p);

        while(i < target.length && !found){
            Log.d("DATE", target[i].getEmail()+target[i].getDate()+target[i].getDepTime()+target[i].getDeparture()+target[i].getDepTime2());
            if((target[i].getEmail()).equals(email) && (target[i].getDate()).equals(date) && (target[i].getDepTime()).equals(dt) &&
                    (target[i].getDeparture()).equals(d) && (target[i].getDepTime2()).equals(dtTwo) && target[i].getDeparture2().equals(dTwo) &&
                target[i].getPrice().equals(p)){
                found = true;
            }
            i++;
        }

        Log.d("DATE", ""+found);
        return found;
    }

}
