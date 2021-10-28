package com.example.lanit.repository;

import com.example.lanit.domain.Car;
import com.example.lanit.domain.Person;
import com.example.lanit.domain.exceptions.CarCantBeSavedException;
import com.example.lanit.domain.exceptions.PersonAlreadyExistException;
import com.example.lanit.dto.Statistics;
import com.example.lanit.repository.crud.CarRepository;
import com.example.lanit.repository.crud.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GeneralRepositoryImpl implements GeneralRepository {

    private final PersonRepository personRepository;
    private final CarRepository carRepository;

    @Autowired
    public GeneralRepositoryImpl(PersonRepository personRepository, CarRepository carRepository) {
        this.personRepository = personRepository;
        this.carRepository = carRepository;
    }

    @Override
    public void savePerson(Person person) {
        if (!personRepository.existsById(person.getId())) {
            personRepository.save(person);
        } else {
            throw new PersonAlreadyExistException();
        }
    }



    @Override
    public void saveCar(Car car) {
        if (checkIfCarCanBeSaved(car.getId(), car.getOwnerId())) {
            carRepository.save(car);
        } else {
            throw new CarCantBeSavedException("Car can't be saved!");
        }
    }

    private boolean checkIfCarCanBeSaved(long carId, long personId) {
        return !carRepository.existsById(carId) && personRepository.findById(personId)
                .map(Person::checkIfPersonIsAdult)
                .orElse(false);
    }

    @Override
    public Optional<Person> findPerson(long id) {
        return personRepository.findById(id);
    }

    @Override
    public Statistics generateStatistics() {
        final long persons = personRepository.count();
        final long cars = carRepository.count();
        final long vendors = carRepository.findAllModels().stream()
                .map(model -> model.split("-")[0].toLowerCase())
                .count();
        return new Statistics(persons, cars, vendors);
    }

    @Override
    public void clearAll() {
        carRepository.deleteAll();
        personRepository.deleteAll();
    }
}
