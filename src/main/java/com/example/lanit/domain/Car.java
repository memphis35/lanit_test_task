package com.example.lanit.domain;

import com.example.lanit.dto.CarDTO;
import com.fasterxml.jackson.annotation.JsonAlias;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
public class Car {
    @Id
    @NotNull(message = "Car: id can't be null")
    private Long id;

    @NotBlank(message = "Car: model can't be blank")
    @NotNull(message = "Car: model can't be null")
    @Pattern(
            regexp = "[a-zA-Z]+-[a-zA-Z0-9- ]+",
            message = "Car: model should match a particular pattern 'VENDOR-MODEL'. " +
                    "VENDOR must contain only letters and at least 1 letter, " +
                    "MODEL must contain only letters, numbers, dashes and spaces and at least one symbol"
    )
    private String model;

    @NotNull(message = "Car: horsepower can't be null")
    @Positive(message = "Car: horsepower must have a positive value")
    @JsonAlias(value = "horsepower")
    private Integer horsePower;

    @Column(name = "owner_id")
    private Long ownerId;

    public Car() {
    }

    public Car(Long id, String model, Integer horsePower, Long ownerId) {
        this.id = id;
        this.model = model;
        this.horsePower = horsePower;
        this.ownerId = ownerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public CarDTO toCarDTO() {
        return new CarDTO(this.id, this.model, this.horsePower);
    }
}
