package com.example.demo.api.forms;

import com.example.demo.api.exceptions.MissingParamException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter @AllArgsConstructor
public class CarForm {

    private Optional<String> brand;
    private Optional<String> model;
    private Optional<String> color;
    private Optional<String> registerNumber;
    private Optional<String> description;
    private Optional<Integer> year;
    private Optional<Integer> price;
    private Optional<Long> ownerId;

    public static void validate(CarForm carForm) {
        if (!carForm.getBrand().isPresent()) {
            throw new MissingParamException("brand");
        }
        if (!carForm.getModel().isPresent()) {
            throw new MissingParamException("model");
        }
        if (!carForm.getColor().isPresent()) {
            throw new MissingParamException("color");
        }
        if (!carForm.getRegisterNumber().isPresent()) {
            throw new MissingParamException("registerNumber");
        }
        if (!carForm.getYear().isPresent()) {
            throw new MissingParamException("year");
        }
        if (!carForm.getPrice().isPresent()) {
            throw new MissingParamException("price");
        }
    }
}
