package com.booking.homestay.admin.controllers;

import com.booking.homestay.admin.dto.UtilityRequest;
import com.booking.homestay.admin.dto.UtilityResponse;
import com.booking.homestay.admin.service.UtilityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/admin/utility")
@AllArgsConstructor
public class UtilityController {

    private final UtilityService utilityService;

    @PostMapping
    public ResponseEntity<Void> createUtility(@RequestBody UtilityRequest utilityRequest) {
        utilityService.save(utilityRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UtilityResponse>> getAllUtility() {
        return status(HttpStatus.OK).body(utilityService.getAllUtility());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilityResponse> getUtilityById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(utilityService.getUtilityById(id));
    }

    @GetMapping("/typeutility/{id}")
    public ResponseEntity<List<UtilityResponse>> getUtilityByType(@PathVariable Long id) {
        return status(HttpStatus.OK).body(utilityService.getUtilityByType(id));
    }

    @PutMapping
    public ResponseEntity<Void> editUtility(@RequestBody UtilityRequest UtilityRequest) {
        utilityService.editUtility(UtilityRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUtility(@PathVariable Long id) {
        utilityService.deleteUtility(id);
        return new ResponseEntity<>("Xóa thành công tiện ích", OK);
    }

}
