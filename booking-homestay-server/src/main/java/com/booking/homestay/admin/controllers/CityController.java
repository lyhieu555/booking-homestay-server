package com.booking.homestay.admin.controllers;

import com.booking.homestay.admin.dto.CityRequest;
import com.booking.homestay.admin.dto.CityResponse;
import com.booking.homestay.admin.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/admin/city")
@AllArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping
    public ResponseEntity<Void> createCity(@RequestBody CityRequest CityRequest) {
        cityService.save(CityRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CityResponse>> getAllCity() {
        return status(HttpStatus.OK).body(cityService.getAllCity());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getCityById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(cityService.getCityById(id));
    }

    @PutMapping
    public ResponseEntity<Void> editCity(@RequestBody CityRequest CityRequest) {
        cityService.editCity(CityRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return new ResponseEntity<>("Xóa thành công Thành phố/ tỉnh", OK);
    }

}
