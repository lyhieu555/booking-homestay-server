package com.booking.homestay.admin.controllers;

import com.booking.homestay.admin.dto.DetailPlaceRequest;
import com.booking.homestay.admin.dto.DetailPlaceResponse;
import com.booking.homestay.admin.service.DetailPlaceService;
import com.booking.homestay.employee.dto.DetailViewRequest;
import com.booking.homestay.employee.dto.DetailViewResponse;
import com.booking.homestay.employee.service.DetailViewService;
import com.booking.homestay.model.DetailPlace;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/admin/detailplace")
@AllArgsConstructor
public class DetailPlaceController {


    private final DetailPlaceService detailPlaceService;

    @PostMapping
    public ResponseEntity<Void> createDetailView(@RequestBody DetailPlaceRequest detailPlaceRequest) {
        detailPlaceService.save(detailPlaceRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<DetailPlaceResponse>> getPlaceByHomeStay(@PathVariable Long id) {
        return status(HttpStatus.OK).body(detailPlaceService.getAllByHomeStay(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDetailPlace(@PathVariable Long id) {
        detailPlaceService.deleteDetailPlace(id);
        return new ResponseEntity<>("Xóa thành công địa điểm chi tiết", OK);
    }


}
