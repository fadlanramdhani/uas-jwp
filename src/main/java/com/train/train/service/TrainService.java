package com.train.train.service;

import java.util.List;
import java.util.Optional;

import com.train.train.domain.Train;

public interface TrainService {
    List<Train> getTrains();

    Optional<Train> findTrainBycode(final String code);
    
    void save(Train Train);

    void delete(final String code);

    void update(final Train train);
}
