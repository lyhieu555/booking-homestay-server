package com.booking.homestay.admin.controllers;

import com.booking.homestay.admin.dto.TypePostRequest;
import com.booking.homestay.admin.dto.TypePostResponse;
import com.booking.homestay.admin.service.TypePostService;
import com.booking.homestay.admin.service.TypePostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/admin/typepost")
@AllArgsConstructor
public class TypePostControllers {

    private final TypePostService typePostService;

    @PostMapping
    public ResponseEntity<Void> createTypePost(@RequestBody TypePostRequest TypePostRequest) {
        typePostService.save(TypePostRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TypePostResponse>> getAllTypePost() {
        return status(HttpStatus.OK).body(typePostService.getAllTypePost());
    }

    @GetMapping("/member")
    public ResponseEntity<List<TypePostResponse>> getAllTypePostMember() {
        return status(HttpStatus.OK).body(typePostService.getAllTypePostMember());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypePostResponse> getTypePostById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(typePostService.getTypePostById(id));
    }

    @PutMapping
    public ResponseEntity<Void> editTypePost(@RequestBody TypePostRequest TypePostRequest) {
        typePostService.editTypePost(TypePostRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTypePost(@PathVariable Long id) {
        typePostService.deleteTypePost(id);
        return new ResponseEntity<>("Xóa thành công loại bài viết", OK);
    }

}
