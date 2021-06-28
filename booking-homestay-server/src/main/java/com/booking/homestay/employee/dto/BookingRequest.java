package com.booking.homestay.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

    private Long id;

    private String fullName;

    private String address;

    private String email;

    private String phone;

    private Date dateIn;

    private Date dateOut;

    private Integer price;

    private Integer depositPrice;

    private String identityCard;

    private Integer costsIncurred;

    private Integer discount;

    private String status;

    private boolean deposit;

    private String description;

    private Instant createDate;

    private Long id_creator;

    private Long id_user;

    private Long id_house;

}
