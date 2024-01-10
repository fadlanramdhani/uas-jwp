package com.train.train.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import com.train.train.domain.Passenger;
import com.train.train.repository.PassengerEntity;
import com.train.train.repository.PassengerEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PassengerServicempl implements PassengerService {
    
    private final PassengerEntityRepository passengerEntityRepository;
    
    private Passenger map(PassengerEntity entity) {
        final Passenger patient = new Passenger();
        patient.setPassId(entity.getPassId());
        patient.setPassengerName(entity.getPassengerName());
        patient.setAddress(entity.getAddress());
        patient.setPhoneNumber(entity.getPhoneNumber());
        return patient;

    }

    private PassengerEntity map(Passenger passenger) {
        final PassengerEntity entity = new PassengerEntity();
        entity.setPassId(passenger.getPassId());
        entity.setPassengerName(passenger.getPassengerName());
        entity.setAddress(passenger.getAddress());
        entity.setPhoneNumber(passenger.getPhoneNumber());
        return entity;

    }

    @Override
    public List<Passenger> getPassengers() {
        final List<PassengerEntity> entities = passengerEntityRepository.findAll();
        return entities.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Passenger> findPassengerByPassId(String passid) {
        Optional<PassengerEntity> optionalPassengerEntity = passengerEntityRepository.findByPassId(passid);
        if (optionalPassengerEntity.isPresent()) {
            return Optional.of(this.map(optionalPassengerEntity.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void save(Passenger passenger) {
        passengerEntityRepository.save(this.map(passenger));
    }

    @Override
    public void delete(String passid) {
        Optional<PassengerEntity> optionalEntity = passengerEntityRepository.findByPassId(passid);
        if (optionalEntity.isPresent()) {
            passengerEntityRepository.delete(optionalEntity.get());
        } else {
            throw new ServiceException("passenger with passid {0} not found" + passid);
        }
    }

    @Override
    public void update(Passenger passenger) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
        
    }
}
