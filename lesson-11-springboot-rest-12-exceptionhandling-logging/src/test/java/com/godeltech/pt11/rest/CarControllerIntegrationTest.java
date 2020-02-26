package com.godeltech.pt11.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.godeltech.pt11.PtApplication;
import com.godeltech.pt11.dto.CarCreateRequest;
import com.godeltech.pt11.dto.CarDto;
import com.godeltech.pt11.entity.Car;
import com.godeltech.pt11.entity.Colour;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;


import java.net.URI;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PtApplication.class)
public class CarControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost:";

    private String contextPath = "/pt11";

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void getAll() {
        ResponseEntity<List<Car>> carsResponseEntity = restTemplate
                .exchange(baseUrl + port + contextPath + "/cars", HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<Car>>() {
                        });
        assertEquals(HttpStatus.OK, carsResponseEntity.getStatusCode());
    }

    @Test
    public void create() throws JsonProcessingException {
        CarCreateRequest carCreateRequest = new CarCreateRequest("Ford", Colour.GREEN);
        ResponseEntity<CarDto> carResponseEntity = restTemplate.exchange(
                RequestEntity.post(URI.create(baseUrl + port + contextPath + "/cars"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(mapper.writeValueAsString(carCreateRequest)), CarDto.class);
        assertEquals(HttpStatus.OK, carResponseEntity.getStatusCode());

    }

    @Test
    public void findByColour() {
        final Colour colour = Colour.GREEN;
        ResponseEntity<List<Car>> carResponseEntity = restTemplate
                .exchange(baseUrl + port + contextPath + "/cars/by-colour/" + colour, HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });
        assertEquals(HttpStatus.OK, carResponseEntity.getStatusCode());
    }

    @Test
    public void findById() {
        ResponseEntity<Car> carResponseEntity = restTemplate
                .exchange(baseUrl + port + contextPath + "/cars/" + 1L, HttpMethod.GET, null, new ParameterizedTypeReference<Car>() {
                });
        assertEquals(HttpStatus.OK, carResponseEntity.getStatusCode());
    }


    @Test
    public void update() throws JsonProcessingException {
        CarDto carDto = new CarDto(2L, "BMW", Colour.RED);
        ResponseEntity<CarDto> employeeResponseEntity = restTemplate.exchange(
                RequestEntity.put(URI.create(baseUrl + port + contextPath + "/cars/"+ 2L))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(mapper.writeValueAsString(carDto)), CarDto.class);
        assertEquals(HttpStatus.OK, employeeResponseEntity.getStatusCode());
    }

    @Test
    public void deleteEmployee() throws JsonProcessingException {
        final Long id = 3L;
        ResponseEntity<Car> employeeResponseEntity = restTemplate
                .exchange(baseUrl + port + contextPath + "/cars/" + id, HttpMethod.DELETE, null, new ParameterizedTypeReference<Car>() {
                });
        assertEquals(HttpStatus.OK, employeeResponseEntity.getStatusCode());
    }

}
