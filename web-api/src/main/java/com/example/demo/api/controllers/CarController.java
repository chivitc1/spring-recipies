package com.example.demo.api.controllers;

import com.example.demo.api.exceptions.ResourceNotFoundException;
import com.example.demo.api.forms.NewCarForm;
import com.example.demo.api.forms.UpdateCarForm;
import com.example.demo.api.views.CarView;
import com.example.demo.api.views.OwnerView;
import com.example.demo.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public CarView create(@RequestBody NewCarForm newCarForm) {
        NewCarForm.validate(newCarForm);
        CarView car = carService.create(newCarForm);
        return car;
    }

    @PatchMapping("/cars/{id}")
    public CarView update(@RequestBody UpdateCarForm udpateCarForm, @PathVariable("id") long id) {
        UpdateCarForm.validate(udpateCarForm);
        CarView car = carService.update(udpateCarForm, id);
        return car;
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        carService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
