package com.example.demo.services;

import com.example.demo.models.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAll();
    Person getById(int id);
    Person create(Person person);
    Person update(int id, Person person);
    void deleteById(int id);
}
