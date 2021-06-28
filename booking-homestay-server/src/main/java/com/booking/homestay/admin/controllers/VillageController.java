package com.booking.homestay.admin.controllers;

import com.booking.homestay.admin.dto.VillageRequest;
import com.booking.homestay.admin.dto.VillageResponse;
import com.booking.homestay.admin.service.VillageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/admin/village")
@AllArgsConstructor
public class VillageController {

    private final VillageService villageService;

    @PostMapping
    public ResponseEntity<Void> createVillage(@RequestBody VillageRequest VillageRequest) {
        villageService.save(VillageRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VillageResponse>> getAllVillage() {
        return status(HttpStatus.OK).body(villageService.getAllVillage());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VillageResponse> getVillageById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(villageService.getVillageById(id));
    }

    @PutMapping
    public ResponseEntity<Void> editVillage(@RequestBody VillageRequest VillageRequest) {
        villageService.editVillage(VillageRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVillage(@PathVariable Long id) {
        villageService.deleteVillage(id);
        return new ResponseEntity<>("Xóa Phường/ Xã thành công", OK);
    }

}
