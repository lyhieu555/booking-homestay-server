package com.booking.homestay.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailUtilityResponse {

    private  Long id;

    private Long id_house;

    private String utilityName;

    private String image;

    private Integer quantity;

    private Long id_utility;


}
