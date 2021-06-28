package com.booking.homestay.employee.controllers;

import com.booking.homestay.employee.dto.ViewRequest;
import com.booking.homestay.employee.dto.ViewResponse;
import com.booking.homestay.employee.service.ViewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/employee/view")
@AllArgsConstructor
public class ViewController {

    private final ViewService viewService;

    @PostMapping
    public ResponseEntity<Void> createView(@RequestBody ViewRequest ViewRequest) {
        viewService.save(ViewRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ViewResponse>> getAllView() {
        return status(HttpStatus.OK).body(viewService.getAllView());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViewResponse> getViewById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(viewService.getViewById(id));
    }

    @PutMapping
    public ResponseEntity<Void> editView(@RequestBody ViewRequest ViewRequest) {
        viewService.editView(ViewRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteView(@PathVariable Long id) {
        viewService.deleteView(id);
        return new ResponseEntity<>("Xóa cảnh quan thành công", OK);
    }

}
