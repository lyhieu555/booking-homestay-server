package com.booking.homestay.admin.controllers;

import com.booking.homestay.admin.dto.TypeUtilityRequest;
import com.booking.homestay.admin.dto.TypeUtilityResponse;
import com.booking.homestay.admin.service.TypeUtilityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/admin/typeutility")
@AllArgsConstructor
public class TypeUtilityController {

    private final TypeUtilityService typeUtilityService;

    @PostMapping
    public ResponseEntity<Void> createTypeUtility(@RequestBody TypeUtilityRequest TypeUtilityRequest) {
        typeUtilityService.save(TypeUtilityRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TypeUtilityResponse>> getAllTypeUtility() {
        return status(HttpStatus.OK).body(typeUtilityService.getAllTypeUtility());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeUtilityResponse> getTypeUtilityById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(typeUtilityService.getTypeUtilityById(id));
    }

    @PutMapping
    public ResponseEntity<Void> editTypeUtility(@RequestBody TypeUtilityRequest TypeUtilityRequest) {
        typeUtilityService.editTypeUtility(TypeUtilityRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTypeUtility(@PathVariable Long id) {
        typeUtilityService.deleteTypeUtility(id);
        return new ResponseEntity<>("Xóa thành công loại tiện ích", OK);
    }

}
