package com.flight.business;

import com.flight.application.Services;
import com.flight.objects.Reservation;
import com.flight.persistence.IHsqldbReservations;

public class ResInfo {
    private Reservation res;
    private IHsqldbReservations resDB;

    public ResInfo(String email, String date, String depTime, String depart, String depTime2, String depart2, String price ){
        try{
            res = new Reservation(email, date, depTime, depart, depTime2, depart2, price);
        } catch (NumberFormatException e){
            System.out.println("ERROR: " +e +" Possibly - ResInfo takes all STRING for CONSTRUCTOR!");
            throw e;
        }
        this.resDB = Services.getReservationPersistence();
    }

    public ResInfo(String email, String date, String depTime, String depart, String depTime2, String depart2, String price, final IHsqldbReservations reservationsPersistence ){
        this(email,date,depTime,depart,depTime2,depart2,price);
        this.resDB = reservationsPersistence;
    }

    public String getEmail(){
        return res.getEmail();
    }

    public String getDate(){
        return res.getDate();
    }

    public String getdepTime() {return res.getDepTime();}

    public String getDepart(){
        return res.getDeparture();
    }

    public String getdepTime2() {return res.getDepTime2();}

    public String getDepart2(){ return res.getDeparture2(); }

    public String getPrice(){return res.getPrice();}

    public Reservation getResInfo(){
        return res;
    }

    public void addResInfo(){
        resDB.insert(res);
    }

    public String toString(){
        return "Email: " + getEmail() +" Date:" +getDate() +"DepTime:"+getdepTime()  +" Dep: "+ getDepart() +"DepTime-2"+getdepTime2()+" Dep-2:"+ getDepart2() +" Price: "+getPrice() ;
    }
}
