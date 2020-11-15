package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/person")
@RestController // To make this class available as a Rest Controller
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@RequestBody Person person){ // @RequestBody = insert the data from the Jason file that coms from the request, and put it into Person person. Ex: The Json from Request Body will contain a key "name". The Person model also has an Json annotation with "name", so the injection is possible
        personService.addPerson(person);
    }


    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }
}
