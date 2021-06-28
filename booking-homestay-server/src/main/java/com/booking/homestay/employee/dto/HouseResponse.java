package com.booking.homestay.employee.dto;

import com.booking.homestay.member.dto.FeedbackResponse;
import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseResponse {

    private Long id;

    private String houseName;

    private String description;

    private Integer amountRoom;

    private Integer price;

    private Integer size;

    private Integer capacity;

    private String image;

    private boolean status;

    private Long id_homeStay;

    private Double scores;

    private Integer star;

    private List<FeedbackResponse> feedbackResponses;


}
