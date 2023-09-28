package com.example.demo.services;

import com.example.demo.exceptions.RecordNotFoundException;
import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonServiceImplementation implements PersonService {

    private final PersonRepository repository;

    public PersonServiceImplementation(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Person> getAll() {
        return repository.findAll();
    }

    @Override
    public Person getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Could not find the person with id: " + id));
    }

    @Override
    public Person create(Person person) {
        repository.saveAndFlush(person);

        return person;
    }

    @Override
    public Person update(int id, Person person) {
        Person personDb = getById(id);

        personDb.setFirstName(person.getFirstName());
        personDb.setLastName(person.getLastName());
        personDb.setEmail(person.getEmail());

        repository.saveAndFlush(personDb);

        return person;
    }

    @Override
    public void deleteById(int id) {
        Person personDb = getById(id);

        repository.delete(personDb);
    }
}
