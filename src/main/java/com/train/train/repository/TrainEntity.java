package com.train.train.repository;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "train")
@Getter
@Setter
public class TrainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "train_name", nullable = false)
    private String trainName;

    @Column(name = "train_route", nullable = false)
    private String trainRoute;

    @Column(name = "train_schedule")
    private Date trainSchedule;
}
