package com.example.lanit.dto;

public class Statistics {
    private final Long personCount;
    private final Long carCount;
    private final Long uniqueVendorCount;

    public Statistics(Long personCount, Long carCount, Long uniqueVendorCount) {
        this.personCount = personCount;
        this.carCount = carCount;
        this.uniqueVendorCount = uniqueVendorCount;
    }

    public Long getPersonCount() {
        return personCount;
    }

    public Long getCarCount() {
        return carCount;
    }

    public Long getUniqueVendorCount() {
        return uniqueVendorCount;
    }
}
