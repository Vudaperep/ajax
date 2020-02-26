package com.godeltech.pt11.converter;

import com.godeltech.pt11.dto.CarDto;
import com.godeltech.pt11.dto.CarCreateRequest;
import com.godeltech.pt11.entity.Car;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {

    @Autowired
    ModelMapper modelMapper;

    public CarDto fromEntityToDto(Car car){
        return modelMapper.map(car, CarDto.class);
    }

    public Car fromDtoToEntity(CarDto carDto){
        return modelMapper.map(carDto, Car.class);
    }

    public Car carSaveFormDtoToEntity(CarCreateRequest carCreateRequest){
        return modelMapper.map(carCreateRequest, Car.class);
    }

}
