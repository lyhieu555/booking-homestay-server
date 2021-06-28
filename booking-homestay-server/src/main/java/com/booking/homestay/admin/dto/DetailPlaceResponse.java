package com.booking.homestay.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailPlaceResponse {

    private  Long id;

    private Long id_homeStay;

    private String placeName;

    private String image;

    private Long id_place;


}
