package com.example.demo.models;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Nullable
    private long id;

    private String brand, model, color;

    @Column(name="reg_no", nullable = true)
    private String registerNumber;

    private int year;
    private double price;

    @Column(name="desc", nullable=true, length=512)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner", nullable = true)
    private Owner owner;
}
