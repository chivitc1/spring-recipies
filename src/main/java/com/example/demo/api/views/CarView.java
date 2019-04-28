package com.example.demo.api.views;

import com.example.demo.models.Car;
import com.example.demo.models.Owner;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CarView {
    private long id;
    private String brand, model, color, registerNumber;
    private int year, price;
    private String description;
    private OwnerView owner;

    public CarView(Car car) {
        this.id = car.getId();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.color = car.getColor();
        this.registerNumber = car.getRegisterNumber();
        this.year = car.getYear();
        this.price = car.getPrice();
        this.description = car.getDescription();
        Owner owner = car.getOwner();
        if (owner != null) {
            this.owner = new OwnerView(car.getOwner());
        }
    }

    @Getter @Setter
    public static class OwnerView {
        private long id;
        private String firstname, lastname;

        public OwnerView(Owner owner) {
            this.id = owner.getOwnerid();
            this.firstname = owner.getFirstname();
            this.lastname = owner.getLastname();
        }
    }
}
