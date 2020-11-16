package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; // used by JSON test.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service //Component is also possible
public class PersonService {

    private final PersonDao personDao;


    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        //@Qualifier("fakeDao") is used to choose the Dao. FakePersonDataAccessService will have

        this.personDao = personDao;
    }

    public int addPerson (Person person){
        return personDao.addPerson(person);
    }

    public List<Person> getAllPeople(){
        return personDao.getPeople();
    }


    public Optional<Person> getPersonById(UUID id) throws JsonProcessingException {
        System.out.println(id);

        // this part is not necessary, is a test to convert objects in JSON using   "jackson-databind" - see pom file
        //Creating the ObjectMapper object
            ObjectMapper mapper = new ObjectMapper();

            Person tempPerson = personDao.getPerson(id).orElse(null);

        //Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(tempPerson);


        System.out.println("My JSON Person:");
            System.out.println(jsonString);
        // end of unnecessary part
        return personDao.getPerson(id);

    }

    public int deletePerson(UUID id){
        return personDao.deletePerson(id);
    }

    public int updatePerson (UUID id, Person person){
        return personDao.updatePerson(id,person);
    }

}
