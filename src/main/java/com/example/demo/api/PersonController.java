package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import org.json.simple.JSONObject;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RequestMapping("api/v1/person")
@RestController // To make this class available as a Rest Controller
public class PersonController {

    private final PersonService personService;

    @Autowired // object for "PersonService" will be stored by spring. With "@Autowired" we instruct spring to inject the variable  "PersonService personService" into the parameters.
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping // the POST requests ( or @RequestMapping("api/v1/person") will be processed here
    public void addPerson(@Valid @NotNull @RequestBody Person person)
    { // @RequestBody = insert the data from the Json file that comes from the request, and put it into Person person. Ex: The Json from Request Body will contain a key "name". The Person model also has an Json annotation with "name", so the injection is possible
        personService.addPerson(person);
        System.out.println(person.getName() + "was successfully added.");
    }


    @GetMapping // the GET  requests (for @RequestMapping("api/v1/person") ) will be processed here
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    // Junior Info :
    // to use a path like http://localhost:8080/api/v1/person/2beffe0c-5c7b-46a6-9545-283c42b3eed9
    // and get the "2beffe0c-5c7b-46a6-9545-283c42b3eed9" in your "id" variable, you must use @PathVariable

    @GetMapping(path ="{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        JSONObject obj = new JSONObject();

        obj.put("name", "foo");
        obj.put("num", 100);

        System.out.println(obj.toJSONString());
        System.out.println("id:"+id.toString());

        try {
            return personService.getPersonById(id).orElse(null);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id,@Valid @NotNull @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }

}
