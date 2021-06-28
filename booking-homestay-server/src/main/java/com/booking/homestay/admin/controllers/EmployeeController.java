package com.booking.homestay.admin.controllers;


import com.booking.homestay.admin.dto.EmployeeRequest;
import com.booking.homestay.admin.dto.EmployeeResponse;
import com.booking.homestay.admin.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/admin/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        employeeService.save(employeeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployee() {
        return status(HttpStatus.OK).body(employeeService.getAllEmployee());
    }

    @GetMapping("/check")
    public ResponseEntity<List<EmployeeResponse>> checkEmployeeWait() {
        return status(HttpStatus.OK).body(employeeService.checkEmployeeWait());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(employeeService.getEmployeeById(id));
    }

    @GetMapping("/lock")
    public ResponseEntity<List<EmployeeResponse>> getEmployeeLock() {
        return status(HttpStatus.OK).body(employeeService.getEmployeeLock());
    }

    @GetMapping("/unlock/{id}")
    public ResponseEntity<String> EmployeeUnlock(@PathVariable Long id) {
         employeeService.EmployeeUnlock(id);
        return new ResponseEntity<>("Mở khóa thành công nhân viên", OK);
    }

    @PutMapping
    public ResponseEntity<Void> editEmployee(@RequestBody EmployeeRequest employeeRequest) {
        employeeService.editEmployee(employeeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Khóa thành công nhân viên", OK);
    }

}
