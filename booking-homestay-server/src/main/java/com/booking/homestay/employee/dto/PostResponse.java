package com.booking.homestay.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private Long id;

    private String title;

    private String description;

    private String createDate;

    private String typePostName ;

    private Long id_typePost ;

    private String userName ;

    private Long id_homeStay ;

}
