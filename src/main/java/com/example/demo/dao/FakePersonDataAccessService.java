package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao") //@Component is also possible. Rockie info : "fakeDao" will be used in PersonService.
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<Person>();

    @Override
    public int addPerson(UUID id, Person person) {
        System.out.println("FakePersonDataAccessService: insertPerson() executed.");
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> getPeople() {
        System.out.println("FakePersonDataAccessService: selectAllPeople() executed.");
        return DB;
    }

    @Override
    public Optional<Person> getPerson(UUID id) {
        System.out.println("FakePersonDataAccessService: selectPersonById,"+ id.toString());
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePerson(UUID id) {
        Optional<Person> personMaybe = this.getPerson(id);
        if (personMaybe.isEmpty()) {
            System.out.println("FakePersonDataAccessService: Person to delete was not found.");
            return 0;
        }
        DB.remove(personMaybe.get());
        System.out.println("FakePersonDataAccessService: Deleted. "+personMaybe.get());
        return 1;
    }

    @Override
    public int updatePerson(UUID id, Person person) {

        Person personToEdit = this.getPerson(id).orElse(null);

        // get the position in the List
        int pos = DB.indexOf(personToEdit);
        if (pos >= 0 && personToEdit != null) { // if we found it, and we edit it.
            DB.set(pos, new Person(personToEdit.getId(), person.getName()));
            System.out.println("FakePersonDataAccessService: Update successful: " + person.getName());
            return 1;
        } else {
            System.out.println("FakePersonDataAccessService: Update unsuccessful.");
        }
        return 0;
    }


}
