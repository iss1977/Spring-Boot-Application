package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    int addPerson(UUID id, Person person);

    default
    int addPerson (Person person){
        UUID id = UUID.randomUUID();
        return addPerson(id, person);
    }

    List<Person> getPeople();

    Optional<Person> getPerson(UUID id);

    int deletePerson(UUID id);

    int updatePerson(UUID id, Person person);


}
