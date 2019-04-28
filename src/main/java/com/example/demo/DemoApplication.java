package com.example.demo;

import com.example.demo.repositories.UserRepository;
import com.example.demo.models.Car;
import com.example.demo.models.Owner;
import com.example.demo.models.User;
import com.example.demo.models.datatypes.SysRoleType;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello Spring Boot 2");
	}

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private UserRepository userRepository;

	@Bean
	CommandLineRunner runner(){
		return args -> {
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
			userRepository.save(new User("user",
					"$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi", SysRoleType.USER));
			// username: admin password: admin
			userRepository.save(new User("admin",
					"$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG", SysRoleType.ADMIN));
		};
	}
}
