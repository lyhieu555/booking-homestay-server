package com.booking.homestay.employee.controllers;


import com.booking.homestay.employee.dto.HouseRequest;
import com.booking.homestay.employee.dto.HouseResponse;
import com.booking.homestay.employee.service.HouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/employee/house")
@AllArgsConstructor
public class HouseController {

    private final HouseService houseService;

    @GetMapping
    public ResponseEntity<List<HouseResponse>> getAllHouseByUser() {
        return status(HttpStatus.OK).body(houseService.getAllHouseByUser());
    }

    @GetMapping("/homestay/{id}")
    public ResponseEntity<List<HouseResponse>> getAllHouseByHomeStay(@PathVariable Long id) {
        return status(HttpStatus.OK).body(houseService.getAllHouseByHomeStay(id));
    }

    @GetMapping("/allhouse")
    public ResponseEntity<List<HouseResponse>> getAllHouse() {
        return status(HttpStatus.OK).body(houseService.getAllHouse());
    }

    @GetMapping("/nolock")
    public ResponseEntity<List<HouseResponse>> getAllHouseNoLock() {
        return status(HttpStatus.OK).body(houseService.getHouseNoLock());
    }

    @GetMapping("/lock/{id}")
    public ResponseEntity<String> LockHouse(@PathVariable Long id) {
        houseService.lockHouse(id);
        return new ResponseEntity<>("Khóa thành công nhà", OK);
    }

    @GetMapping("/unlock/{id}")
    public ResponseEntity<String> UnLockHouse(@PathVariable Long id) {
        houseService.unlockHouse(id);
        return new ResponseEntity<>("Mở khóa thành công nhà", OK);
    }

    @PostMapping
    public ResponseEntity<Long> createHouse(@RequestBody HouseRequest houseRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(houseService.save(houseRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseResponse> getHouseById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(houseService.getHouseById(id));
    }

    @PutMapping
    public ResponseEntity<Void> editHouse(@RequestBody HouseRequest houseRequest) {
        houseService.editHouse(houseRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHouse(@PathVariable Long id) {
        houseService.deleteHouse(id);
        return new ResponseEntity<>("Xóa thành công nhà", OK);
    }

}
