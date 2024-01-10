package com.train.train.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Passenger {
    private String passid;

    @NotBlank(message = "passenger name is required")
    @Size(min = 3, max = 50)
    private String passengerName;

    @NotBlank(message = "address is required")
    @Size(min = 3, max = 50)
    private String address;

    @NotBlank(message = "phonenumber is required")
    @Size(min = 3, max = 50)
    private String phoneNumber;
    
    public Passenger() {
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassId() {
        return passid;
    }

    public void setPassId(String passid) {
        this.passid = passid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
