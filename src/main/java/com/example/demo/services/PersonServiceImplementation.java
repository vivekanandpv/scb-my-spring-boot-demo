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
        return repository.findAll()
                .stream()
                .map(this::toViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public PersonViewModel getById(int id) {
        return toViewModel(getEntityById(id));
    }

    @Override
    public PersonViewModel create(PersonCreateViewModel viewModel) {
        return toViewModel(repository.saveAndFlush(toEntity(viewModel)));
    }

    @Override
    public PersonViewModel update(int id, PersonUpdateViewModel viewModel) {
        Person entityDb = getEntityById(id);
        BeanUtils.copyProperties(viewModel, entityDb);
        return toViewModel(repository.saveAndFlush(entityDb));
    }

    @Override
    public void deleteById(int id) {
        Person personDb = getEntityById(id);
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

    private Person toEntity(PersonCreateViewModel viewModel) {
        Person entity = new Person();
        BeanUtils.copyProperties(viewModel, entity);
        return entity;
    }

    private Person getEntityById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Could not find the person with id: " + id));
    }
}
