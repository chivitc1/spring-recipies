package com.example.demo.api.views;

import com.example.demo.models.Owner;
import lombok.Getter;

@Getter
public class OwnerView {
    private long id;
    private String firstname, lastname;

    public OwnerView(Owner owner) {
        this.id = owner.getOwnerid();
        this.firstname = owner.getFirstname();
        this.lastname = owner.getLastname();
    }
}
