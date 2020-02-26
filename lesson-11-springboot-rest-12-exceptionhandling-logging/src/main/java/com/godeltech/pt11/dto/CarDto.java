package com.godeltech.pt11.dto;

import com.godeltech.pt11.entity.Colour;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CarDto {

    private Long id;
    private String model;
    private Colour colour;
}
