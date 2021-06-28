package com.booking.homestay.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailPlaceRequest {

    private Long id;

    private Long id_homeStay;

    private Long id_place;
}
