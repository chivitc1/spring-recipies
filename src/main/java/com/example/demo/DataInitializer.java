package com.example.demo;

import com.example.demo.models.Car;
import com.example.demo.models.Owner;
import com.example.demo.models.User;
import com.example.demo.models.datatypes.SysRoleType;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.OwnerRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Save demo data to database
        Owner owner1 = Owner.builder().firstname("John").lastname("Johnson").build();
        Owner owner2 = Owner.builder().firstname("Mary").lastname("Robinson").build();
        ownerRepository.save(owner1);
        ownerRepository.save(owner2);

        Car car1 = Car.builder().brand("Ford")
                .model("Mustang")
                .description("Description x1")
                .color("Red")
                .registerNumber("ADF-1121")
                .year(2017)
                .price(59000)
                .owner(owner1).build();
        Car car2 = Car.builder().brand("Nissan")
                .model("Leaf")
                .description("Description x2")
                .color("White")
                .registerNumber("SSJ-3002")
                .year(2014)
                .price(29000)
                .owner(owner2).build();
        Car car3 = Car.builder().brand("Toyota")
                .model("Prius")
                .description("Description x3")
                .color("Silver")
                .registerNumber("KKO-0212")
                .year(2018)
                .price(39000)
                .owner(owner2).build();
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);

        // username: user password: user
        Set<SysRoleType> roleList1 = new HashSet<>();
        roleList1.add(SysRoleType.USER);
        Set<SysRoleType> roleList2 = new HashSet<>();
        roleList2.add(SysRoleType.ADMIN);
        userRepository.save(new User("user",
                passwordEncoder.encode("user"), roleList1));
        // username: admin password: admin
        userRepository.save(new User("admin",
                passwordEncoder.encode("admin"), roleList2));
        userRepository.save(new User("admin2",
                passwordEncoder.encode("admin"), roleList2));
    }
}
