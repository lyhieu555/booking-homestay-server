package com.booking.homestay.admin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private Long id;

    private String userName;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private String address;

    private String image;

    private String role;

    private String createdDate;

    private String dateOfBirth;

    private String sex;

    private boolean enabled;

    private boolean status;

    private Long id_creator;

    private Long id_homeStay;

    private String homeStayName;
}
