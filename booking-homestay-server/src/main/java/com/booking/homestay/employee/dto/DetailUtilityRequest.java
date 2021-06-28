package com.booking.homestay.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailUtilityRequest {

    private Long id;

    private Long id_house;

    private Long id_utility;

    private Integer quantity;
}
