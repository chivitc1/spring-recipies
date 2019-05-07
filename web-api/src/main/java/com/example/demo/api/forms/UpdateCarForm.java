package com.example.demo.api.forms;

import com.example.demo.api.exceptions.NaNParamException;
import com.example.demo.api.exceptions.NonNullParamException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Getter @AllArgsConstructor
public class UpdateCarForm {
    private Optional<String> brand;
    private Optional<String> model;
    private Optional<String> color;
    private Optional<String> registerNumber;
    private Optional<String> description;
    private Optional<String> year;
    private Optional<String> price;
    private Optional<Long> ownerId;

    public static void validate(UpdateCarForm updateCarForm) {
        if (updateCarForm.getBrand().isPresent() && StringUtils.isEmpty(updateCarForm.getBrand().get())) {
            throw new NonNullParamException("brand");
        }
        if (updateCarForm.getModel().isPresent() && StringUtils.isEmpty(updateCarForm.getModel().get())) {
            throw new NonNullParamException("model");
        }
        if (updateCarForm.getColor().isPresent() && StringUtils.isEmpty(updateCarForm.getColor().get())) {
            throw new NonNullParamException("color");
        }
//        if (!newCarForm.getRegisterNumber().isPresent()) {
//            throw new MissingParamException("registerNumber");
//        }
        if (updateCarForm.getYear().isPresent()) {
            String strYear = updateCarForm.getYear().get();
            try {
                Integer.parseInt(strYear);
            } catch (Exception ex) {
                throw new NaNParamException("year");
            }
        }
        if (updateCarForm.getPrice().isPresent()) {
            String strPrice = updateCarForm.getPrice().get();
            try {
                Double.parseDouble(strPrice);
            } catch (Exception ex) {
                throw new NaNParamException("price");
            }
        }
    }
}
