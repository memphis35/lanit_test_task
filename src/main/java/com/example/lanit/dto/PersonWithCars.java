package com.example.lanit.dto;

import java.util.Date;
import java.util.List;

public class PersonWithCars {

    private final Long id;
    private final String name;
    private final Date birthDate;
    private final List<CarDTO> cars;

    public PersonWithCars(Long id, String name, Date birthDate, List<CarDTO> cars) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.cars = cars;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public List<CarDTO> getCars() {
        return cars;
    }
}
