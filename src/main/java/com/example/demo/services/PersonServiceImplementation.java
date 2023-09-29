package com.example.demo.services;

import com.example.demo.exceptions.RecordNotFoundException;
import com.example.demo.models.Car;
import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.viewmodels.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonServiceImplementation implements PersonService {

    private final PersonRepository repository;

    public PersonServiceImplementation(PersonRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<PersonViewModel> getAll() {
        return null;
    }

    @Override
    public PersonViewModel getById(int id) {
        return null;
    }

    @Override
    public PersonViewModel create(PersonCreateViewModel viewModel) {
        return null;
    }

    @Override
    public PersonViewModel update(int id, PersonUpdateViewModel viewModel) {
        return null;
    }

    @Override
    public void deleteById(int id) {
        Person personDb = getById(id);

        repository.delete(personDb);
    }

    private PersonViewModel toViewModel(Person entity) {
        PersonViewModel viewModel = new PersonViewModel();
        BeanUtils.copyProperties(entity, viewModel);
        viewModel.setCars(
                entity.getCars()
                        .stream()
                        .map(this::toViewModel)
                        .collect(Collectors.toList())
        );
        return viewModel;
    }

    private CarViewModel toViewModel(Car entity) {
        CarViewModel viewModel = new CarViewModel();
        BeanUtils.copyProperties(entity, viewModel);
        return viewModel;
    }

    private Car toEntity(CarCreateViewModel viewModel) {
        Car entity = new Car();
        BeanUtils.copyProperties(viewModel, entity);
        return entity;
    }

    private Person getEntityById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Could not find the person with id: " + id));
    }
}
