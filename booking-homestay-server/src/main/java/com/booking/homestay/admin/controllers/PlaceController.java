package com.booking.homestay.admin.controllers;

import com.booking.homestay.admin.dto.PlaceRequest;
import com.booking.homestay.admin.dto.PlaceResponse;
import com.booking.homestay.admin.service.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/admin/place")
@AllArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping
    public ResponseEntity<Void> createPlace(@RequestBody PlaceRequest PlaceRequest) {
        placeService.save(PlaceRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlaceResponse>> getAllPlace() {
        return status(HttpStatus.OK).body(placeService.getAllPlace());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceResponse> getPlaceById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(placeService.getPlaceById(id));
    }

    @PutMapping
    public ResponseEntity<Void> editPlace(@RequestBody PlaceRequest PlaceRequest) {
        placeService.editPlace(PlaceRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);
        return new ResponseEntity<>("Xóa thành công địa điểm", OK);
    }

}
