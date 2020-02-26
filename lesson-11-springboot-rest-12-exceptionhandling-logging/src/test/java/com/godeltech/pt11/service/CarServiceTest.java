package com.godeltech.pt11.service;

import com.godeltech.pt11.converter.CarConverter;
import com.godeltech.pt11.dto.CarCreateRequest;
import com.godeltech.pt11.dto.CarDto;
import com.godeltech.pt11.entity.Car;
import com.godeltech.pt11.entity.Colour;
import com.godeltech.pt11.exception.CarNotFoundException;
import com.godeltech.pt11.repository.CarRepository;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    private final static Long CAR_ID = 2L;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Mock
    private CarConverter carConverter;

    @Test
    public void CreateTest() {
        CarCreateRequest carCreateRequest = new CarCreateRequest("Tesla", Colour.BLACK);
        Car car = new Car();
        car.setModel("Tesla");
        car.setColour(Colour.BLACK);
        Car car1 = new Car(CAR_ID, "Tesla", Colour.BLACK);
        CarDto carDto = new CarDto(CAR_ID, "Tesla", Colour.BLACK);
        when(carConverter.carSaveFormDtoToEntity(carCreateRequest)).thenReturn(car);
        when(carConverter.fromEntityToDto(car1)).thenReturn(carDto);
        when(carRepository.save(car)).thenReturn(car1);
        assertEquals(carService.create(carCreateRequest), carDto);
        verify(carConverter).carSaveFormDtoToEntity(carCreateRequest);
        verify(carRepository).save(car);
        verify(carConverter).fromEntityToDto(car1);
    }

    @Test
    public void FindOneTest() {
        long id = 1L;
        Optional carFromDb = Optional.of(new Car(1L, "Tesla", Colour.BLACK));
        CarDto carDto = new CarDto(1L, "Tesla", Colour.BLACK);
        when(carRepository.findById(id)).thenReturn(carFromDb);
        when(carConverter.fromEntityToDto((Car) carFromDb.get())).thenReturn(carDto);
        carService.findOne(id);
        verify(carRepository).findById(id);
        verify(carConverter).fromEntityToDto((Car) carFromDb.get());

    }

    @Test
    public void FindAllTest() {
        Car car1 = new Car(CAR_ID, "Tesla", Colour.BLACK);
        CarDto carDto1 = new CarDto(CAR_ID, "Tesla", Colour.BLACK);
        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car1);
        when(carRepository.findAll()).thenReturn(carList);
        when(carConverter.fromEntityToDto(car1)).thenReturn(carDto1);
        carService.findAll();
        verify(carConverter, times(2)).fromEntityToDto(car1);
        verify(carRepository).findAll();
    }

    @Test
    public void DeleteTest() {
        when(carRepository.existsById(CAR_ID)).thenReturn(true);
        carService.delete(CAR_ID);
        verify(carRepository).existsById(CAR_ID);
        verify(carRepository).deleteById(CAR_ID);
    }

    @Test
    public void UpdateTest() throws NotFoundException {
        Car car1 = new Car(CAR_ID, "Tesla", Colour.BLACK);
        CarDto carDto = new CarDto(CAR_ID, "Tesla", Colour.BLACK);
        when(carRepository.existsById(CAR_ID)).thenReturn(true);
        when(carConverter.fromDtoToEntity(carDto)).thenReturn(car1);
        when(carConverter.fromEntityToDto(car1)).thenReturn(carDto);
        when(carRepository.save(car1)).thenReturn(car1);
        carService.update(CAR_ID, carDto);
        verify(carRepository).save(car1);
        verify(carConverter).fromEntityToDto(car1);
        verify(carConverter).fromDtoToEntity(carDto);
    }

    @Test(expected = CarNotFoundException.class)
    public void UpdateNotFoundTest() throws CarNotFoundException, NotFoundException {
        CarDto carDto = new CarDto(CAR_ID, "Tesla", Colour.BLACK);
        carService.update(CAR_ID, carDto);
    }

    @Test
    public void FindByColourTest() {
        Car car1 = new Car(CAR_ID, "Tesla", Colour.BLACK);
        CarDto carDto = new CarDto(CAR_ID, "Tesla", Colour.BLACK);
        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car1);
        when(carConverter.fromEntityToDto(car1)).thenReturn(carDto);
        when(carRepository.findCarByColour(car1.getColour())).thenReturn(carList);
        carService.findByColour(car1.getColour());
        verify(carConverter, times(2)).fromEntityToDto(car1);
    }
}
