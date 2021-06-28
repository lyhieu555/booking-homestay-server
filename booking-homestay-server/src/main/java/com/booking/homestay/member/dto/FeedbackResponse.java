package com.booking.homestay.member.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponse {

    private Long id;

    private String content;

    private String createDate;

    private Integer rate ;

    private String userName;

    private String image;

    private String houseName;

    private Long id_house;

    private Long id_user;

}
