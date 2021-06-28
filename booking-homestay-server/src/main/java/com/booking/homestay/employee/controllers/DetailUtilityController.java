package com.booking.homestay.employee.controllers;

import com.booking.homestay.employee.dto.*;
import com.booking.homestay.employee.service.DetailUtilityService;
import com.booking.homestay.employee.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/employee/detailutility")
@AllArgsConstructor
public class DetailUtilityController {

    private final DetailUtilityService detailUtilityService;

    @PostMapping
    public ResponseEntity<Void> createDetailUtility(@RequestBody DetailUtilityRequest detailUtilityRequest) {
        detailUtilityService.save(detailUtilityRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("room/{id}")
    public ResponseEntity<List<DetailUtilityResponse>> getDetailUtilityByIdHouse(@PathVariable Long id) {
        return status(HttpStatus.OK).body(detailUtilityService.getAllByHouse(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailUtilityResponse> getDetailUtilityById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(detailUtilityService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDetailUtility(@PathVariable Long id) {
        detailUtilityService.deleteDetailUtility(id);
        return new ResponseEntity<>("Đã xóa thành công tiện ích chi tiết", OK);
    }

    @PutMapping
    public ResponseEntity<Void> EditDetailUtility(@RequestBody DetailUtilityRequest detailUtilityRequest) {
        detailUtilityService.editDetailUtility(detailUtilityRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
