package com.train.train.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Train {
    private String code;

    @NotBlank(message = "train name is required")
    @Size(min = 3, max = 50)
    private String trainName;

    private String trainRoute;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "train schedule is required")
    private Date trainSchedule;


    public Train(){
    }

    public String getTrainName(){
        return trainName;
    }

    public void setTrainName(String trainname) {
        this.trainName = trainname;
    }

    public String getTrainRoute(){
        return trainRoute;
    }

    public void setTrainRoute(String trainroute) {
        this.trainRoute = trainroute;
    }
    
    public String getCode(){
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getTrainSchedule(){
        return trainSchedule;
    }

    public void setTrainSchedule(Date trainschedule) {
        this.trainSchedule = trainschedule;
    }
}
