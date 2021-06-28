package com.booking.homestay.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequest {


    private Long id;

    private String content;

    private Instant createDate;

    private Integer rate ;

    private Long id_house;

    private Long id_user;

}
