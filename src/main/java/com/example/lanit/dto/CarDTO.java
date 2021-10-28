package com.example.lanit.dto;

public class CarDTO {

    private final Long id;
    private final String format;
    private final Integer horsePower;

    public CarDTO(Long id, String format, Integer horsePower) {
        this.id = id;
        this.format = format;
        this.horsePower = horsePower;
    }

    public Long getId() {
        return id;
    }

    public String getFormat() {
        return format;
    }

    public Integer getHorsePower() {
        return horsePower;
    }
}
