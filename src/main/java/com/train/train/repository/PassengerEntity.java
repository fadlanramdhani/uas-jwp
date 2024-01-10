package com.train.train.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "passenger")
@Getter
@Setter
public class PassengerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "passid", nullable = false)
    private String passId;

    @Column(name = "passenger_name")
    private String passengerName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;
}
