package com.booking.homestay.employee.controllers;

import com.booking.homestay.employee.dto.DetailViewRequest;
import com.booking.homestay.employee.dto.DetailViewResponse;
import com.booking.homestay.employee.service.DetailViewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/employee/detailview")
@AllArgsConstructor
public class DetailViewController {


    private final DetailViewService detailViewService;

    @PostMapping
    public ResponseEntity<Void> createDetailView(@RequestBody DetailViewRequest detailViewRequest) {
        detailViewService.save(detailViewRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<DetailViewResponse>> getViewByHouse(@PathVariable Long id) {
        return status(HttpStatus.OK).body(detailViewService.getAllByHouse(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDetailView(@PathVariable Long id) {
        detailViewService.deleteDetailView(id);
        return new ResponseEntity<>("Xóa thành công quan cảnh chi tiết", OK);
    }


}
