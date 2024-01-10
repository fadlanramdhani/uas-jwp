package com.train.train.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerEntityRepository extends JpaRepository<PassengerEntity, Long> {
    
    Optional<PassengerEntity> findByPassId(final String passid);
}
