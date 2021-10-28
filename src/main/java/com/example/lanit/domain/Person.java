package com.example.lanit.domain;

import com.example.lanit.dto.CarDTO;
import com.example.lanit.dto.PersonWithCars;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Person {
    @Id
    @NotNull(message = "Person: id can't be null")
    private Long id;

    @NotBlank(message = "Person: name can't be null or blank")
    private String name;

    @NotNull(message = "Person: birth date can't be null")
    @Past(message = "Person: birth date must be in the past")
    @JsonAlias("birthdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date birthDate;

    @OneToMany(targetEntity = Car.class, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "owner_id")
    private List<Car> cars;

    public Person() {
    }

    public Person(Long id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public boolean checkIfPersonIsAdult() {
        final LocalDate now = LocalDate.now();
        final LocalDate birthLocalDate = Instant.ofEpochMilli(this.birthDate.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return birthLocalDate.until(now).getYears() >= 18;
    }

    public PersonWithCars toPersonWithCars() {
        final List<CarDTO> cars = this.cars.stream()
                .map(Car::toCarDTO)
                .collect(Collectors.toList());
        return new PersonWithCars(this.id, this.name, this.birthDate, cars);
    }
}
