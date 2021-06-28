package com.booking.homestay.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewRequest {

    private Long id;

    private String viewName;

    private String image;

}
