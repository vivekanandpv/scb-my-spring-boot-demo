package com.example.demo.services;

import com.example.demo.exceptions.RecordNotFoundException;
import com.example.demo.models.Car;
import com.example.demo.models.Person;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.viewmodels.CarCreateViewModel;
import com.example.demo.viewmodels.CarUpdateViewModel;
import com.example.demo.viewmodels.CarViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImplementation implements CarService {
    private final CarRepository repository;
    private final PersonRepository personRepository;

    public CarServiceImplementation(
            CarRepository repository,
            PersonRepository personRepository
    ) {
        this.repository = repository;
        this.personRepository = personRepository;
    }

    @Override
    public List<CarViewModel> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public CarViewModel getById(int id) {
        return toViewModel(getEntityById(id));
    }

    @Override
    public CarViewModel create(CarCreateViewModel viewModel) {
        Car entity = toEntity(viewModel);

        Person person = personRepository.findById(viewModel.getPersonId())
                .orElseThrow(() -> new RecordNotFoundException("Could not find the Person with id: " + viewModel.getPersonId()));

        person.getCars().add(entity);
        entity.setPersonId(person.getPersonId());
        entity.setPerson(person);

        Car entityDb = repository.saveAndFlush(entity);
        return toViewModel(entityDb);
    }

    @Override
    public CarViewModel update(int id, CarUpdateViewModel viewModel) {
        Car entityDb = getEntityById(id);
        BeanUtils.copyProperties(viewModel, entityDb);

        return toViewModel(repository.saveAndFlush(entityDb));
    }

    @Override
    public void deleteById(int id) {
        Car entityDb = getEntityById(id);
        repository.delete(entityDb);
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

    private Car getEntityById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Could not find the car with id: " + id));
    }
}
