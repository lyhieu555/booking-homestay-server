package com.booking.homestay.employee.controllers;

import com.booking.homestay.employee.dto.PostRequest;
import com.booking.homestay.employee.dto.PostResponse;
import com.booking.homestay.employee.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/employee/post")
@AllArgsConstructor
public class PostControllers {

    private final PostService postService;

    @GetMapping("/all/{typepost}")
    public ResponseEntity<List<PostResponse>> getAllPostByType(@PathVariable Long typepost) {
        return status(HttpStatus.OK).body(postService.getAllPostByType(typepost));
    }

    @GetMapping("/all/member/{typepost}")
    public ResponseEntity<List<PostResponse>> getAllPostByTypeMember(@PathVariable Long typepost) {
        return status(HttpStatus.OK).body(postService.getAllPostByTypeMember(typepost));
    }


    @GetMapping()
    public ResponseEntity<List<PostResponse>> getAllPost() {
        return status(HttpStatus.OK).body(postService.getAllPost());
    }

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest PostRequest) {
        postService.save(PostRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPostById(id));
    }


    @PutMapping
    public ResponseEntity<Void> editPost(@RequestBody PostRequest PostRequest) {
        postService.editPost(PostRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Xóa bài viết thành công", OK);
    }

}
