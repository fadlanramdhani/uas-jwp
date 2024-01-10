package com.train.train.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainEntityRepository extends JpaRepository<TrainEntity, Long> {
    
    Optional<TrainEntity> findByCode(final String code);
}
