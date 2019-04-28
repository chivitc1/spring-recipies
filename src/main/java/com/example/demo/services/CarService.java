package com.example.demo.services;

import com.example.demo.api.forms.CarForm;
import com.example.demo.api.views.CarView;
import com.example.demo.api.views.OwnerView;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<CarView> getAllCars();

    Optional<CarView> findById(long id);

    Optional<OwnerView> findCarOwner(long id);

    CarView create(CarForm carForm);
}
