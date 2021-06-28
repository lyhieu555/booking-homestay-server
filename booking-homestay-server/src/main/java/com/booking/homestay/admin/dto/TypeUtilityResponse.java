package com.booking.homestay.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeUtilityResponse {

    private Long id;

    private String typeName;

    private List<UtilityResponse> utility;

}
