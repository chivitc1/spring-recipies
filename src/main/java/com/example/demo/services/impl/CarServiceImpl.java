package com.example.demo.services.impl;

import com.example.demo.api.forms.CarForm;
import com.example.demo.api.views.CarView;
import com.example.demo.api.views.OwnerView;
import com.example.demo.models.Car;
import com.example.demo.models.Owner;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.OwnerRepository;
import com.example.demo.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, OwnerRepository ownerRepository) {
        this.carRepository = carRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<CarView> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(CarView::new).collect(Collectors.toList());
    }

    @Override
    public Optional<CarView> findById(long id) {
        Optional<Car> car = carRepository.findById(id);
        if (!car.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(new CarView(car.get()));
    }

    @Override
    public Optional<OwnerView> findCarOwner(long id) {
        Optional<Car> car = carRepository.findById(id);
        if (!car.isPresent() || car.get().getOwner() == null) {
            return Optional.empty();
        }
        return Optional.of(new OwnerView(car.get().getOwner()));
    }

    @Override
    public CarView create(CarForm carForm) {
        Optional<Owner> owner = Optional.empty();
        if (carForm.getOwnerId().isPresent()) {
            owner = ownerRepository.findById(carForm.getOwnerId().get());
        }
        Car newCar = Car.builder()
                .brand(carForm.getBrand().get())
                .model(carForm.getModel().get())
                .color(carForm.getColor().get())
                .registerNumber(carForm.getRegisterNumber().get())
                .price(carForm.getPrice().get())
                .year(carForm.getYear().get())
                .description(carForm.getDescription().orElse(""))
                .owner(owner.orElse(null))
                .build();
        Car carEntity = carRepository.saveAndFlush(newCar);
        return new CarView(carEntity);
    }
}
