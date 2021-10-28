package com.example.lanit.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PersonTest {

    private final Person person = new Person();
    private final ZoneId zoneId = ZoneId.systemDefault();

    @Test
    public void whenPersonIsAdult_thenSuccess() {
        final long unixTime = LocalDate.now().minusYears(20).atStartOfDay(zoneId).toInstant().toEpochMilli();
        person.setBirthDate(new Date(unixTime));

        Assertions.assertTrue(person.checkIfPersonIsAdult());
    }

    @Test
    public void whenPersonIsLessThan18ByOneDay_thenSuccess() {
        final long unixTime = LocalDate.now().minusYears(18).plusDays(1).atStartOfDay(zoneId).toInstant().toEpochMilli();
        person.setBirthDate(new Date(unixTime));

        Assertions.assertFalse(person.checkIfPersonIsAdult());
    }

    @Test
    public void whenPersonIs18_thenSuccess() {
        final long unixTime = LocalDate.now().minusYears(18).atStartOfDay(zoneId).toInstant().toEpochMilli();
        person.setBirthDate(new Date(unixTime));

        Assertions.assertTrue(person.checkIfPersonIsAdult());
    }

    @Test
    public void whenPersonIsNotAdult_thenSuccess() {
        final long unixTime = LocalDate.now().minusYears(16).atStartOfDay(zoneId).toInstant().toEpochMilli();
        person.setBirthDate(new Date(unixTime));

        Assertions.assertFalse(person.checkIfPersonIsAdult());
    }
}
