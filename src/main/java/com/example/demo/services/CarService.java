package com.example.demo.services;

import com.example.demo.viewmodels.CarCreateViewModel;
import com.example.demo.viewmodels.CarUpdateViewModel;
import com.example.demo.viewmodels.CarViewModel;

import java.util.List;

public interface CarService {
    List<CarViewModel> getAll();
    CarViewModel getById(int id);
    CarViewModel create(CarCreateViewModel viewModel);
    CarViewModel update(int id, CarUpdateViewModel viewModel);
    void deleteById(int id);
}
