package com.booking.homestay.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingHistoryResponse {

    private Long Id;

    private String houseName;

    private String fullName;

    private String address;

    private String email;

    private String phone;

    private Date dateIn;

    private Date dateOut;

    private int price;

    private Integer costsIncurred;

    private Integer discount;

    private String description;

    private String createDate;

    private String creatorName;

    private Long id_booking;

}
