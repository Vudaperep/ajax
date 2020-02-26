package com.godeltech.pt11.repository;

import com.godeltech.pt11.entity.Car;
import com.godeltech.pt11.entity.Colour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findCarByColour(Colour colour);
}
