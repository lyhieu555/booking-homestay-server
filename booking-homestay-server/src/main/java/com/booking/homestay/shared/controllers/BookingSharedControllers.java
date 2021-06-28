package com.booking.homestay.shared.controllers;

import com.booking.homestay.employee.dto.BookingResponse;
import com.booking.homestay.employee.dto.HouseResponse;
import com.booking.homestay.employee.service.BookingService;
import com.booking.homestay.shared.dto.SearchRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/booking")
@AllArgsConstructor
public class BookingSharedControllers {

    private final BookingService bookingService;

    @GetMapping("/paypal/{id}")
    public ResponseEntity<BookingResponse> paypalBooking(@PathVariable Long id) {
        return status(HttpStatus.OK).body(bookingService.getCheckBooking(id));
    }

    @GetMapping("/submitpaypal/{id}")
    public ResponseEntity<String> submitPaypal(@PathVariable Long id) {
        bookingService.submitBooking(id);
        return new ResponseEntity<>("Bạn đã thanh toán thành công tiền cọc", OK);
    }

    @GetMapping("/seachbooking/{phone}&{idBook}")
    public ResponseEntity<List<BookingResponse>> seachBooking(@PathVariable String phone, @PathVariable Long idBook) {
        return status(HttpStatus.OK).body(bookingService.seachBooking(phone, idBook));
    }

    @PostMapping("/searchhouse")
    public ResponseEntity<List<HouseResponse>> searchHouse(@RequestBody SearchRequest searchRequest) {
        return status(HttpStatus.OK).body( bookingService.searchHouse(searchRequest));
    }

}
