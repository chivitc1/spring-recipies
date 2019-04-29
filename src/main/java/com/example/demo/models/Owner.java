package com.example.demo.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long ownerid;
    private String firstname, lastname;

    /**
     * mappedBy meaning:
     * The field that owns the relationship. Required unless
     * the relationship is unidirectional.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Car> cars;
}
