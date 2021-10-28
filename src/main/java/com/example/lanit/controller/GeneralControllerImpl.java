package com.example.lanit.controller;

import com.example.lanit.domain.Car;
import com.example.lanit.domain.Person;
import com.example.lanit.domain.exceptions.PersonNotFoundException;
import com.example.lanit.dto.PersonWithCars;
import com.example.lanit.dto.Statistics;
import com.example.lanit.repository.GeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class GeneralControllerImpl implements GeneralController {

    private final GeneralRepository repository;

    private final String jsonType = MediaType.APPLICATION_JSON_VALUE;

    @Autowired
    public GeneralControllerImpl(GeneralRepository repository) {
        this.repository = repository;
    }

    @PostMapping(path = "person", produces = jsonType, consumes = jsonType)
    public void savePerson(@Valid @RequestBody Person person) {
        repository.savePerson(person);
    }

    @PostMapping(path = "car", produces = jsonType, consumes = jsonType)
    public void saveCar(@Valid @RequestBody Car car) {
        repository.saveCar(car);
    }

    @GetMapping(path = "personwithcars", produces = jsonType)
    public PersonWithCars findPerson(@NotNull(message = "personId can't be null")
                                     @RequestParam(name = "personid", required = false) Integer personId) {
        return repository.findPerson(personId)
                .map(Person::toPersonWithCars)
                .orElseThrow(PersonNotFoundException::new);
    }

    @GetMapping(path = "statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    public Statistics getStatistics() {
        return repository.generateStatistics();
    }

    @GetMapping(path = "/clear", produces = jsonType)
    public void clearRepository() {
        repository.clearAll();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
