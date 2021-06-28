package com.booking.homestay.admin.controllers;

import com.booking.homestay.admin.dto.DistrictRequest;
import com.booking.homestay.admin.dto.DistrictResponse;
import com.booking.homestay.admin.service.DistrictService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/admin/district")
@AllArgsConstructor
public class DistrictController {

    private final DistrictService districtService;

    @PostMapping
    public ResponseEntity<Void> createDistrict(@RequestBody DistrictRequest DistrictRequest) {
        districtService.save(DistrictRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DistrictResponse>> getAllDistrict() {
        return status(HttpStatus.OK).body(districtService.getAllDistrict());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistrictResponse> getDistrictById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(districtService.getDistrictById(id));
    }

    @GetMapping("/city/{cityName}")
    public ResponseEntity<List<DistrictResponse>> getDistrictByCity(@PathVariable String cityName) {
        return status(HttpStatus.OK).body(districtService.getAllDistrictbyCity(cityName));
    }

    @PutMapping
    public ResponseEntity<Void> editDistrict(@RequestBody DistrictRequest DistrictRequest) {
        districtService.editDistrict(DistrictRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDistrict(@PathVariable Long id) {
        districtService.deleteDistrict(id);
        return new ResponseEntity<>("Xóa thành công Quận/ Huyện", OK);
    }

}
