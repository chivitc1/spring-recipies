package com.example.demo.repositories;

import com.example.demo.models.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrandOrderByYearAsc(String brand);
    List<Car> findByColor(String color);
    List<Car> findByYear(int year);
    List<Car> findByYearAndColor(int year, String color);
    @Query("select c from Car c where c.brand like %:brand")
    List<Car> findByBrandEndsWith(@Param("brand") String brand);
    List<Car> findAll(Sort sort);
    Page<Car> findAll(Pageable pageable);

    @Query("SELECT c FROM Car c LEFT JOIN FETCH c.owner")
    List<Car> findAll();
}
