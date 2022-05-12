package com.angelo.apiwatercount.repositories;

import com.angelo.apiwatercount.models.Person;
import jdk.internal.org.jline.terminal.impl.PosixSysTerminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonRepository {
    // HashMap creates an array associate with key:value
    private final static HashMap<Integer, Person> persons = new HashMap<>();

    private int generateId(final int possible) {
        if (persons.containsKey(possible)) {
            return generateId(possible + 1);
        }

        return possible;
    }

    public List<Person> getAll() {
        return new ArrayList<Person>(persons.values());
    }

    public Person get(final int id) {
        return persons.get(id);
    }

    public void add(final Person person) {
        if (person.getId() == 0) {
            person.setId(generateId(persons.size() + 1));
        }

        persons.put(person.getId(), person);
    }

    public void edit(final Person person) {
        persons.remove(person.getId());
        persons.put(person.getId(), person);
    }

    public void delete(final int id) {
        persons.remove(id);
    }
}
