package com.godeltech.pt11.dto;

import com.godeltech.pt11.entity.Colour;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class CarCreateRequest {

    @NotBlank
    @Length(min = 3)
    private String model;
    private Colour colour;
}
