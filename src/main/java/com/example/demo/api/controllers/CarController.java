package com.example.demo.api.controllers;

import com.example.demo.api.exceptions.ResourceNotFoundException;
import com.example.demo.api.forms.CarForm;
import com.example.demo.api.views.CarView;
import com.example.demo.api.views.OwnerView;
import com.example.demo.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public Iterable<CarView> getCars() {
        return carService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public CarView getCar(@PathVariable("id") long id) {
        return carService.findById(id).orElseThrow(() -> new ResourceNotFoundException());
    }

    @GetMapping("/cars/{id}/owner")
    public OwnerView getCarOwner(@PathVariable("id") long id) {
        OwnerView owner = carService.findCarOwner(id).orElseThrow(() -> new ResourceNotFoundException());
        return owner;
    }

    @PostMapping("/cars")
    public CarView create(@RequestBody CarForm carForm) {
        CarForm.validate(carForm);
        CarView car = carService.create(carForm);
        return car;
    }
}
