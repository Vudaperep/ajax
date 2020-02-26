package com.godeltech.pt11.service;

import com.godeltech.pt11.converter.CarConverter;
import com.godeltech.pt11.dto.CarCreateRequest;
import com.godeltech.pt11.dto.CarDto;
import com.godeltech.pt11.entity.Car;
import com.godeltech.pt11.entity.Colour;
import com.godeltech.pt11.exception.CarNotFoundException;
import com.godeltech.pt11.exception.ColourNotFoundException;
import com.godeltech.pt11.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    private final CarConverter carConverter;

    public List<CarDto> findAll() {
        return carRepository.findAll().stream()
                .map(carConverter::fromEntityToDto)
                .collect(Collectors.toList());
    }


    public CarDto findOne(Long id) {
        return carConverter.fromEntityToDto(carRepository
                .findById(id)
                .orElseThrow(()->new CarNotFoundException(id)));
    }

    public CarDto create(CarCreateRequest carCreateRequest) {
        Car newCar = carConverter.carSaveFormDtoToEntity(carCreateRequest);
        return carConverter.fromEntityToDto(carRepository.save(newCar));
    }

    public void delete(Long id) {
        if(!carRepository.existsById(id)){
            throw new CarNotFoundException(id);
        }
        carRepository.deleteById(id);
    }

    public CarDto update(Long id,CarDto carDto) {
        if (!carRepository.existsById(id)) {
            throw new CarNotFoundException(id);
        } else {
            carDto.setId(id);
            Car save = carRepository.save(carConverter.fromDtoToEntity(carDto));
            carConverter.fromEntityToDto(save);
        }
        return carDto;
    }

    public List<CarDto> findByColour(Colour colour) {
        if(carRepository.findCarByColour(colour).isEmpty()){
            throw new ColourNotFoundException(colour);
        }
        return carRepository.findCarByColour(colour)
                .stream()
                .map(carConverter::fromEntityToDto)
                .collect(Collectors.toList());
    }

}
