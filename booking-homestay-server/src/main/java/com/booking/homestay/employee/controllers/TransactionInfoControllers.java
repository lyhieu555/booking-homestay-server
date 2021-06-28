package com.booking.homestay.employee.controllers;

import com.booking.homestay.employee.dto.TransactionInfoResponse;
import com.booking.homestay.employee.service.TransactionInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employee/transactioninfo")
@AllArgsConstructor
public class TransactionInfoControllers {
    private final TransactionInfoService transactionInfoService;

    @GetMapping
    public ResponseEntity<List<TransactionInfoResponse>> getAllTransByHouseHomestay(){
        return ResponseEntity.status(HttpStatus.OK).body(transactionInfoService.getAllByHouseHomestay());
    }
}
