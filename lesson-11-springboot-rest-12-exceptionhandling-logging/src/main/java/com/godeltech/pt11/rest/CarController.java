package com.godeltech.pt11.rest;

import com.godeltech.pt11.dto.CarCreateRequest;
import com.godeltech.pt11.dto.CarDto;
import com.godeltech.pt11.entity.Colour;
import com.godeltech.pt11.service.CarService;
import com.godeltech.pt11.swagger.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
@Validated
public class CarController {

    private final CarService carService;

    @GetAllCarApiDocumentation
    @GetMapping
    public List<CarDto> carList() {
        return carService.findAll();
    }

    @FindOneCarApiDocumentation
    @GetMapping("/{id}")
    public CarDto findCar(@Valid @Positive @PathVariable Long id) {
        return carService.findOne(id);
    }

    @CreateCarApiDocumentation
    @PostMapping
    public CarDto createCar(@Valid @RequestBody CarCreateRequest carCreateRequest) {
        return carService.create(carCreateRequest);
    }

    @DeleteCarApiDocumentation
    @DeleteMapping("/{id}")
    public void deleteCar(@Valid @Positive @PathVariable Long id) {
        carService.delete(id);
    }

    @UpdateCarApiDocumentation
    @PutMapping("/{id}")
    public CarDto updateCar(@Valid @Positive @PathVariable Long id, @RequestBody CarDto carDto) {
        return carService.update(id, carDto);
    }

    @FindByColourApiDocumentation
    @GetMapping("by-colour/{colour}")
    public List<CarDto> findByColour(@PathVariable Colour colour) {
        return carService.findByColour(colour);
    }
}
