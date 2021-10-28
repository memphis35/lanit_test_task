package com.example.lanit.repository;

import com.example.lanit.domain.Car;
import com.example.lanit.domain.Person;
import com.example.lanit.dto.Statistics;

import java.util.Optional;

public interface GeneralRepository {

    void savePerson(Person person);

    void saveCar(Car car);

    Optional<Person> findPerson(long id);

    Statistics generateStatistics();

    void clearAll();
}
