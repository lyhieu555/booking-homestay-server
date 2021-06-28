package com.booking.homestay.employee.controllers;

import com.booking.homestay.employee.dto.BookingRequest;
import com.booking.homestay.employee.dto.BookingResponse;
import com.booking.homestay.employee.dto.HouseResponse;
import com.booking.homestay.employee.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/employee/booking")
@AllArgsConstructor
public class BookingControllers {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookingNotCheckIn() {
        return status(HttpStatus.OK).body(bookingService.getAllBookingNotCheckIn());
    }

    @GetMapping("/checkin")
    public ResponseEntity<List<BookingResponse>> getAllBookingCheckIn() {
        return status(HttpStatus.OK).body(bookingService.getAllBookingCheckIn());
    }

    @GetMapping("/checkout")
    public ResponseEntity<List<BookingResponse>> getAllBookingCheckOut() {
        return status(HttpStatus.OK).body(bookingService.getAllBookingCheckOut());
    }

    @GetMapping("/cancellation")
    public ResponseEntity<List<BookingResponse>> getAllGeneralApplicationCancellation() {
        return status(HttpStatus.OK).body(bookingService.getAllGeneralApplicationCancellation());
    }

    @PostMapping
    public ResponseEntity<Void> createBooking(@RequestBody BookingRequest bookingRequest) {
        bookingService.save(bookingRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/member")
    public ResponseEntity<Void> createBookingMember(@RequestBody BookingRequest bookingRequest) {
        bookingService.saveMember(bookingRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> editBooking(@RequestBody BookingRequest bookingRequest) {
        bookingService.editBookingCheckIn(bookingRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/notcheckin")
    public ResponseEntity<Void> editBookingNotCheckIn(@RequestBody BookingRequest bookingRequest) {
        bookingService.editBookingNotCheckIn(bookingRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/addidentitycard")
    public ResponseEntity<Void> addIdentityCard(@RequestBody BookingRequest bookingRequest) {
        bookingService.addIdentityCard(bookingRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(bookingService.getBookingById(id));
    }

    @GetMapping("/checkdate/{id}")
    public ResponseEntity<List<LocalDate>> checkDateByHouse(@PathVariable Long id) {
        return status(HttpStatus.OK).body(bookingService.checkDateByHouse(id));
    }

    @GetMapping("/checkdateedit/{idHouse}&{idBook}")
    public ResponseEntity<List<LocalDate>> checkDateEditByHouse(@PathVariable Long idHouse, @PathVariable Long idBook) {
        return status(HttpStatus.OK).body(bookingService.checkDateEditByHouse(idHouse, idBook));
    }

    @GetMapping("checkin/{id}")
    public ResponseEntity<String> checkIn(@PathVariable Long id) {
        bookingService.checkIn(id);
        return new ResponseEntity<>("Thủ tục nhân phòng thành công", OK);
    }

    @GetMapping("checkout/{id}")
    public ResponseEntity<String> checkOut(@PathVariable Long id) {
        bookingService.checkOut(id);
        return new ResponseEntity<>("Thủ tục trả phòng thành công", OK);
    }

    @GetMapping("processing/{id}")
    public ResponseEntity<String> Processing(@PathVariable Long id) {
        bookingService.processing(id);
        return new ResponseEntity<>("Hủy đơn xác nhân hoàn cọc", OK);
    }

    @GetMapping("refunded/{id}")
    public ResponseEntity<String> Refunded(@PathVariable Long id) {
        bookingService.refunded(id);
        return new ResponseEntity<>("Xác nhận đã hoàn cọc", OK);
    }

    @GetMapping("cancel/{id}")
    public ResponseEntity<String> Cancel(@PathVariable Long id) {
        bookingService.cancel(id);
        return new ResponseEntity<>("Hủy đơn thành công", OK);
    }


}
