package com.example.demo.models;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Nullable
    private long id;
    private String brand, model, color, registerNumber;
    private int year, price;

    @Column(name="desc", nullable=false, length=512)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner", nullable = true)
    private Owner owner;
}
