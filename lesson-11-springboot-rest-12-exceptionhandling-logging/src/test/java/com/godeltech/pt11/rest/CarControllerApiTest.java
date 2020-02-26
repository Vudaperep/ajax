package com.godeltech.pt11.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.godeltech.pt11.dto.CarCreateRequest;
import com.godeltech.pt11.dto.CarDto;
import com.godeltech.pt11.entity.Colour;
import com.godeltech.pt11.exception.CarNotFoundException;
import com.godeltech.pt11.exception.ColourNotFoundException;
import com.godeltech.pt11.service.CarService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CarController.class)
public class CarControllerApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarService carService;

    private static final String URI = "/cars";

    @Test
    public void createCarTestShouldReturnBadRequestIfModelBlank()
            throws Exception {
        CarCreateRequest carCreateDto = new CarCreateRequest();
        carCreateDto.setModel("T");
        carCreateDto.setColour(Colour.BLACK);
        mockMvc.perform(post(URI)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(carCreateDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("error").value("Invalid data supplied!"));;

    }

    @Test
    public void carWithIdNotFoundTest() throws Exception {
        final Long id = 123213L;
        Mockito.doThrow(new CarNotFoundException(id)).when(carService).findOne(id);
        mockMvc.perform(get("/cars/" + id)
                .contentType("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error").value("Car with id:123213 not found"));;
    }

    @Test
    public void carWithWrongColourNotFound() throws Exception {
        Colour colour = Colour.BLACK;
        Mockito.doThrow(new ColourNotFoundException(colour)).when(carService).findByColour(colour);
        mockMvc.perform(get("/cars/by-colour/" + "black")
                .contentType("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error").value("Car with this colour:BLACK not found"));;
    }

    @Test
    public void carWithWrongIdNotUpdate() throws Exception {
        final Long id = 12312312L;
        CarDto carDto = new CarDto(12312312L,"Tesla", Colour.GREEN);
        Mockito.doThrow(new CarNotFoundException(id)).when(carService).update(id,carDto);
        mockMvc.perform(put("/cars/" + id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(carDto)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error").value("Car with id:12312312 not found"));;
    }

    @Test
    public void carWithWrongIdNotDelete() throws Exception {
        final Long id = 12312312L;
        Mockito.doThrow(new CarNotFoundException(id)).when(carService).delete(id);
        mockMvc.perform(delete("/cars/" + id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error").value("Car with id:12312312 not found"));
    }
}


