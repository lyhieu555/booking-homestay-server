package com.booking.homestay.admin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeStayRequest {

    private Long id;

    private String homeStayName;

    private String description;

    private String phone;

    private String image;

    private Instant createDate;

    private boolean status;

    private String villageName;

    private String cityName;

    private String districtName;

}
