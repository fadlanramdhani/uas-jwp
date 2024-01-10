package com.train.train.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import com.train.train.domain.Train;
import com.train.train.repository.TrainEntity;
import com.train.train.repository.TrainEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service

public class TrainServicempl implements TrainService {
    
    private final TrainEntityRepository trainEntityRepository;

    private Train map(TrainEntity entity) {
        final Train train = new Train();
        train.setCode(entity.getCode());
        train.setTrainName(entity.getTrainName());
        train.setTrainRoute(entity.getTrainRoute());
        train.setTrainSchedule(entity.getTrainSchedule());
        return train;

    }
    private TrainEntity map(Train train) {
        final TrainEntity entity = new TrainEntity();
        entity.setCode(train.getCode());
        entity.setTrainName(train.getTrainName());
        entity.setTrainRoute(train.getTrainRoute());
        entity.setTrainSchedule(train.getTrainSchedule());
        return entity;

    }

    @Override
    public List<Train> getTrains() {
        final List<TrainEntity> entities = trainEntityRepository.findAll();
        return entities.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Train> findTrainBycode(String code) {
        Optional<TrainEntity> optionalTrainEntity = trainEntityRepository.findByCode(code);
        if (optionalTrainEntity.isPresent()) {
            return Optional.of(this.map(optionalTrainEntity.get()));
        } else {
            return Optional.empty();

        }
    }

    @Override
    public void save(Train train) {
        trainEntityRepository.save(this.map(train));
    }

    @Override
    public void delete(String code) {
         Optional<TrainEntity> optionalEntity = trainEntityRepository.findByCode(code);
        if (optionalEntity.isPresent()) {
            trainEntityRepository.delete(optionalEntity.get());
        } else {
            throw new ServiceException("train with code {0} not found" + code);
        }
    }

    @Override
    public void update(Train train) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
