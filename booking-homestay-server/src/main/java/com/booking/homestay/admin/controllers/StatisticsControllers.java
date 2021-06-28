package com.booking.homestay.admin.controllers;

import com.booking.homestay.admin.dto.statistics.AccountResponse;
import com.booking.homestay.admin.dto.statistics.DateRequest;
import com.booking.homestay.admin.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/admin/statistics")
@AllArgsConstructor
public class StatisticsControllers {

    private final StatisticsService statisticsService;


    @GetMapping("/allaccount")
    public ResponseEntity<List<AccountResponse>> getAllAccount() {
        return status(HttpStatus.OK).body(statisticsService.getAllAccount());
    }

    @PostMapping("/homestay")
    public ResponseEntity<List<AccountResponse>> getAllHomeStay(@RequestBody DateRequest dateRequest) {
        return status(HttpStatus.OK).body(statisticsService.getAllHomeStay(dateRequest));
    }

}
