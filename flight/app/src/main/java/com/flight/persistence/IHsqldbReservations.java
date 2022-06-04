package com.flight.persistence;

import com.flight.objects.Reservation;

public interface IHsqldbReservations {
    void insert(Reservation saveThis);
    Reservation[] search(String email);
}
