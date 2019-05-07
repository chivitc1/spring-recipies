package com.example.demo.services.impl;

import com.example.demo.api.exceptions.ResourceNotFoundException;
import com.example.demo.api.forms.NewCarForm;
import com.example.demo.api.forms.UpdateCarForm;
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
    public CarView create(NewCarForm newCarForm) {
        Optional<Owner> owner = Optional.empty();
        if (newCarForm.getOwnerId().isPresent()) {
            owner = ownerRepository.findById(newCarForm.getOwnerId().get());
        }
        Car newCar = Car.builder()
                .brand(newCarForm.getBrand().get())
                .model(newCarForm.getModel().get())
                .color(newCarForm.getColor().get())
                .registerNumber(newCarForm.getRegisterNumber().orElse(""))
                .price(newCarForm.getPrice().get())
                .year(newCarForm.getYear().get())
                .description(newCarForm.getDescription().orElse(""))
                .owner(owner.orElse(null))
                .build();
        Car carEntity = carRepository.saveAndFlush(newCar);
        return new CarView(carEntity);
    }

    @Override
    public void remove(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarView update(UpdateCarForm updateCarForm, long id) {
        Optional<Car> optCar = carRepository.findById(id);
        Car car = optCar.orElseThrow(() -> new ResourceNotFoundException());
        if (updateCarForm.getBrand().isPresent()) {
            car.setBrand(updateCarForm.getBrand().get());
        }
        if (updateCarForm.getModel().isPresent()) {
            car.setModel(updateCarForm.getModel().get());
        }
        if (updateCarForm.getColor().isPresent()) {
            car.setColor(updateCarForm.getColor().get());
        }
        if (updateCarForm.getPrice().isPresent()) {
            car.setPrice(Double.parseDouble(updateCarForm.getPrice().get()));
        }
        if (updateCarForm.getPrice().isPresent()) {
            car.setYear(Integer.parseInt(updateCarForm.getYear().get()));
        }
        Car carEntity = carRepository.saveAndFlush(car);
        return new CarView(carEntity);
    }
}
