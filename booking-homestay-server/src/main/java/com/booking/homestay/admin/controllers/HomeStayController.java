package com.booking.homestay.admin.controllers;


import com.booking.homestay.admin.dto.HomeStayRequest;
import com.booking.homestay.admin.dto.HomeStayResponse;
import com.booking.homestay.admin.service.HomeStayService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/admin/homestay")
@AllArgsConstructor
public class HomeStayController {

    private final HomeStayService homeStayService;

    @PostMapping
    public ResponseEntity<Long> createHomeStay(@RequestBody HomeStayRequest homeStayRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(homeStayService.save(homeStayRequest));
    }

    @GetMapping
    public ResponseEntity<List<HomeStayResponse>> getAllHomeStay() {
        return status(HttpStatus.OK).body(homeStayService.getAllHomeStay());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HomeStayResponse> getHomeStayById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(homeStayService.getHomeStayById(id));
    }

    @GetMapping("/lock")
    public ResponseEntity<List<HomeStayResponse>> getHomeStayLock() {
        return status(HttpStatus.OK).body(homeStayService.getHomeStayLock());
    }

    @GetMapping("/unlock/{id}")
    public ResponseEntity<String> HomeStayUnlock(@PathVariable Long id) {
        homeStayService.HomStayUnlock(id);
        return new ResponseEntity<>("Mở khóa thành công home stay", OK);
    }

    @PutMapping
    public ResponseEntity<Void> editHomeStay(@RequestBody HomeStayRequest homeStayRequest) {
        homeStayService.editHomeStay(homeStayRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHomeStay(@PathVariable Long id) {
       homeStayService.deleteHomeStay(id);
        return new ResponseEntity<>("Khóa thành công home stay", OK);
    }


}
