package com.booking.homestay.employee.controllers;

import com.booking.homestay.employee.dto.MemberRequest;
import com.booking.homestay.employee.dto.MemberResponse;
import com.booking.homestay.employee.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/employee/member")
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberRequest memberRequest) {
        memberService.save(memberRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMember() {
        return status(HttpStatus.OK).body(memberService.getAllMember());
    }


    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(memberService.getMemberById(id));
    }

    @GetMapping("/lock")
    public ResponseEntity<List<MemberResponse>> getMemberLock() {
        return status(HttpStatus.OK).body(memberService.getMemberLock());
    }

    @GetMapping("/unlock/{id}")
    public ResponseEntity<String> MemberUnlock(@PathVariable Long id) {
        memberService.MemberUnlock(id);
        return new ResponseEntity<>("Mở khóa thành công khách hàng", OK);
    }

    @PutMapping
    public ResponseEntity<Void> editMember(@RequestBody MemberRequest memberRequest) {
        memberService.editMember(memberRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>("Khóa thành công khách hàng", OK);
    }

}
