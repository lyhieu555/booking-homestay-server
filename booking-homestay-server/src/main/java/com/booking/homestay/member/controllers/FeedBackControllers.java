package com.booking.homestay.member.controllers;

import com.booking.homestay.admin.dto.CityResponse;
import com.booking.homestay.employee.dto.PostRequest;
import com.booking.homestay.member.dto.FeedbackRequest;
import com.booking.homestay.member.dto.FeedbackResponse;
import com.booking.homestay.member.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/member/feedback")
@AllArgsConstructor
public class FeedBackControllers {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Void> createFeedBack(@RequestBody FeedbackRequest feedbackRequest) {
        feedbackService.save(feedbackRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResponse>> getAllCity() {
        return status(HttpStatus.OK).body(feedbackService.getAllFeedBack());
    }


}
