package com.booking.homestay.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    private Long id;

    private String title;

    private String description;

    private Instant createDate;

    private Long id_typePost ;

    private Long id_user ;

    private Long id_homeStay ;

}
