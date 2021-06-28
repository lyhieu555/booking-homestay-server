package com.booking.homestay.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {

    private Long id;

    private String fullName;

    private String address;

    private String email;

    private String phone;

    private Date dateIn;

    private Date dateOut;

    private Integer price;

    private String identityCard;

    private Integer costsIncurred;

    private Integer discount;

    private Integer depositPrice;

    private boolean deposit;

    private String description;

    private String status;

    private String createDate;

    private String creatorName;

    private String userName;

    private String houseName;

    private String homestayName;

    private Long id_house;

    private List<BookingHistoryResponse> bookingHistoryResponses;

}
