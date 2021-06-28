package com.booking.homestay.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {

    private Date dateIn;

    private Date dateOut;

    private Integer capacity;

}
