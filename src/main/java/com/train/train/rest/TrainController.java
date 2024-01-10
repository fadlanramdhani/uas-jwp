package com.train.train.rest;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.train.train.domain.Train;
import com.train.train.service.TrainService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TrainController {
    private final TrainService trainService;

    @GetMapping("/trains")
    public String getTrains(Model model) {
        model.addAttribute("trains", trainService.getTrains());
        return "trains";
    }

    
    @GetMapping("/signup-train")
    public String showSignupForm(Train train) {
        return "add-trains";
    }

    @PostMapping("/trains")
    public String addTrains(@Valid Train train, BindingResult bindingResult, Model model) {
        String code = train.getCode();

        boolean exists = trainService.findTrainBycode(code).isPresent();

        if (exists) {
            throw new IllegalArgumentException("train with code:" + code + "is already exist");
        }

        trainService.save(train);

        model.addAttribute("trains", trainService.getTrains());
        return "trains";
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    @GetMapping(value = "/trains/{code}")
    public ResponseEntity<Train> findTrain(@PathVariable("code") String code) {
        Optional<Train> trainOptional = trainService.findTrainBycode(code);
        if (trainOptional.isPresent()) {
            return new ResponseEntity<>(trainOptional.get(), HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PostMapping(value = "/trains/{code}")
    public String updateTrain(@PathVariable("code") String code,
            Train train,
            BindingResult result, Model model) {

        final Optional<Train> optionalTrain = trainService.findTrainBycode(train.getCode());
        if (optionalTrain.isEmpty()) {
            throw new ServiceException("train with code:" + code + "is not exists");
        }

        trainService.update(train);

        model.addAttribute("trains", trainService.getTrains());
        return "redirect:/trains";
    }

    @GetMapping("/edit/{code}")
    public String showUpdateForm(@PathVariable("code") String code, Model model) {
        final Optional<Train> optionalTrain = trainService.findTrainBycode(code);
        if (optionalTrain.isEmpty()) {
            throw new ServiceException("train with code:" + code + "is not exists");
        }
        final Train trainToBeUpdated = optionalTrain.get();

        model.addAttribute("train", trainToBeUpdated);
        return "update-trains";
    }

    @GetMapping(value = "/trains/{code}/delete")
    public String deleteTrain(@PathVariable("code") String code) {
        trainService.delete(code);
        return "redirect:/trains";
    }
}
