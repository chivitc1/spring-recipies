package com.example.demo.api.forms;

import com.example.demo.api.exceptions.MissingParamException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter @AllArgsConstructor
public class NewCarForm {

    private Optional<String> brand;
    private Optional<String> model;
    private Optional<String> color;
    private Optional<String> registerNumber;
    private Optional<String> description;
    private Optional<Integer> year;
    private Optional<Double> price;
    private Optional<Long> ownerId;

    public static void validate(NewCarForm newCarForm) {
        if (!newCarForm.getBrand().isPresent()) {
            throw new MissingParamException("brand");
        }
        if (!newCarForm.getModel().isPresent()) {
            throw new MissingParamException("model");
        }
        if (!newCarForm.getColor().isPresent()) {
            throw new MissingParamException("color");
        }
//        if (!newCarForm.getRegisterNumber().isPresent()) {
//            throw new MissingParamException("registerNumber");
//        }
        if (!newCarForm.getYear().isPresent()) {
            throw new MissingParamException("year");
        }
        if (!newCarForm.getPrice().isPresent()) {
            throw new MissingParamException("price");
        }
    }
}
