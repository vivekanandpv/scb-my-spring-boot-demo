package com.example.demo.viewmodels;

import java.util.ArrayList;
import java.util.List;

public class PersonViewModel {
    private int personId;
    private String firstName;
    private String lastName;
    private String email;

    private List<CarViewModel> cars = new ArrayList<>();

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CarViewModel> getCars() {
        return cars;
    }

    public void setCars(List<CarViewModel> cars) {
        this.cars = cars;
    }
}

