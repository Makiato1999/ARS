package com.flight.objects;

public class Reservation {
    //holds a user created Reservation

    private String email;
    private String departure;
    private String departure2;
    private String depTime;
    private String depTime2;
    private String price;
    private String date;

    public Reservation(){
        //do nothing
    }

    public Reservation(String e, String d, String dt, String depart, String dt2, String depart2, String p ){
        email = e;
        date = d;
        depTime = dt;
        departure = depart;
        depTime2 = dt2;
        departure2 = depart2;
        price = p;

    }

    public String getEmail() {
        return email;
    }

    public String getDate(){
        return date;
    }

    public String getDepTime() {return depTime;}

    public String getDepTime2() { return depTime2; }

    public String getDeparture(){
        return departure;
    }

    public String getDeparture2(){
        return departure2 ;
    }

    public String getPrice(){
        return price;
    }


}
