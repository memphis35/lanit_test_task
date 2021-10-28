package com.example.lanit.repository.crud;

import com.example.lanit.domain.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CarRepository extends CrudRepository<Car, Long> {

    @Query(value = "SELECT c.model FROM Car c")
    Collection<String> findAllModels();
}
