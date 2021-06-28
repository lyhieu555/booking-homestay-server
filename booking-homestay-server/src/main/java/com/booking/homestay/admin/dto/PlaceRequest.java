package com.booking.homestay.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRequest {

    private Long id;

    private String placeName;

    private String image;
}
