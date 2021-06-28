package com.booking.homestay.admin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictRequest {

    private Long id;

    private String districtName;

    private String type;

    private String cityName;

}
