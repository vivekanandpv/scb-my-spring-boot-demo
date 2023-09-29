package com.example.demo.services;

import com.example.demo.models.Person;
import com.example.demo.viewmodels.PersonCreateViewModel;
import com.example.demo.viewmodels.PersonUpdateViewModel;
import com.example.demo.viewmodels.PersonViewModel;

import java.util.List;

public interface PersonService {
    List<PersonViewModel> getAll();
    PersonViewModel getById(int id);
    PersonViewModel create(PersonCreateViewModel viewModel);
    PersonViewModel update(int id, PersonUpdateViewModel viewModel);
    void deleteById(int id);
}
