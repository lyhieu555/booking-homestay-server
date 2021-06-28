package com.booking.homestay.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeStayResponse {

    private Long id;

    private String homeStayName;

    private String description;

    private String phone;

    private String image;

    private String createDate;

    private boolean status;

    private String villageName;

    private String cityName;

    private String districtName;

    private String placeName;

    private List<DetailPlaceResponse> detailPlace;

}
