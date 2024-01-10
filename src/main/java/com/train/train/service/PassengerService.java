package com.train.train.service;

import java.util.List;
import java.util.Optional;

import com.train.train.domain.Passenger;

public interface PassengerService {
    List<Passenger> getPassengers();

    Optional<Passenger> findPassengerByPassId(final String passid);

    void save(Passenger passenger);

    void delete(final String passid);

    void update(final Passenger passenger);
    
}
