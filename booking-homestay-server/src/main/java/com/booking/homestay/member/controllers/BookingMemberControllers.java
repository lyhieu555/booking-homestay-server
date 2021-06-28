package com.booking.homestay.member.controllers;

import com.booking.homestay.employee.dto.BookingResponse;
import com.booking.homestay.employee.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/member/booking")
@AllArgsConstructor
public class BookingMemberControllers {

    private final BookingService bookingService;


    @GetMapping()
    public ResponseEntity<List<BookingResponse>> getAllMember() {
        return status(HttpStatus.OK).body(bookingService.getAllMember());
    }

}
