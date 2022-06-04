package com.flight.business;

import com.flight.application.Services;
import com.flight.objects.Reservation;
import com.flight.persistence.IHsqldbReservations;

import java.util.ArrayList;

public class ResSearchHandler {
    private String email;
    private Reservation[] res;
    private IHsqldbReservations resDB;
    private ArrayList<ResInfo> ri;
    //Constructor takes in String email;
    public ResSearchHandler(String email){
        this.email = email;
        this.resDB = Services.getReservationPersistence();
    }

//    public static void main(String[] args){
//
//        ResInfo ri = new ResInfo("dev@dp","YWG","YYC","340.99","20/13/2022");
//        ri.addResInfo();
//        ResSearchHandler rh = new ResSearchHandler("dev@dp");
//        System.out.println("SearchEmail: "+rh.searchEmail());
//
//    }

    public ResSearchHandler(final IHsqldbReservations reservationsPersistence, String email) {
        this(email);
        this.resDB = reservationsPersistence;
    }

    //checks to see if the email exist
    //if it does than only call getResTable() method that will return array list
    public boolean emailExist(){
        boolean result;
        if(getResTable().isEmpty() == true){
            result = false;
        } else { //false
            result = true;
        }
        return result;
    }

    //returns an arraylist of ResInfo for UI to display.
    public ArrayList<ResInfo> getResTable(){
        ri= new ArrayList<ResInfo>();
        res = resDB.search(email);
        if(res != null){
            for(int i = 0; i < res.length; i ++){
                String email = res[i].getEmail();
                String dep = res[i].getDeparture();
                String arr = res[i].getDeparture2();
                String pri = res[i].getPrice();
                String dt = res[i].getDate();

                ri.add(new ResInfo(res[i].getEmail(),res[i].getDate(),res[i].getDepTime(),res[i].getDeparture(),res[i].getDepTime2(),res[i].getDeparture2(),res[i].getPrice()));
            }
        }

        return ri;
    }

    //Prints ResInfo ArrayList in a orderly fashion.
    public String toString(){
        String out = "";
        for(int i = 0; i < ri.size(); i++){
            out += ri.get(i).toString() + "\n";
        }
        return out;
    }
}
