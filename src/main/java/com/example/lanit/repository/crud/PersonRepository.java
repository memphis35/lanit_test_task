package com.example.lanit.repository.crud;

import com.example.lanit.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
