package com.booking.homestay.employee.service;

import com.booking.homestay.employee.dto.PostRequest;
import com.booking.homestay.employee.dto.PostResponse;
import com.booking.homestay.employee.mapper.PostMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.*;
import com.booking.homestay.repository.*;
import com.booking.homestay.shared.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class PostService {

    private final AuthService authService;
    private final PostMapper postMapper;
    private final IPostRepository iPostRepository;


    public void save(PostRequest postRequest) {
        Optional<Post> posts = iPostRepository.findByTitle(postRequest.getTitle());
        if (posts.isPresent()) {
            throw new SpringException("Tiêu đề bài viết đã tồn tại");
        }
        iPostRepository.save(postMapper.map(postRequest, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPostByType(Long typepostId) {
        if (authService.getCurrentUser().getHomeStay()==null) {
            return iPostRepository.findByTypePost_Id(typepostId)
                    .stream()
                    .map(postMapper::mapToDto)
                    .collect(toList());
        } else {
            return iPostRepository.findByTypePost_IdAndHomeStayOrTypePost_IdAndHomeStayNull(typepostId,authService.getCurrentUser().getHomeStay(), typepostId)
                    .stream()
                    .map(postMapper::mapToDto)
                    .collect(toList());
        }
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPostByTypeMember(Long typepostId) {
            return iPostRepository.findByTypePost_Id(typepostId)
                    .stream()
                    .map(postMapper::mapToDto)
                    .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPost() {
            return iPostRepository.findAll()
                    .stream()
                    .map(postMapper::mapToDto)
                    .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> countPost(Long id) {
        return iPostRepository.findByTypePost_Id( id)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    public void deletePost(Long id) {
        iPostRepository.deleteById(id);
    }

    public void editPost(PostRequest postRequest) {
        Post post = iPostRepository.findById(postRequest.getId()).orElseThrow(() -> new SpringException("Không tồn tại bài viết ID - " + postRequest.getId()));
        Optional<Post> postName = iPostRepository.findByTitle(postRequest.getTitle());
        if (!postName.isPresent()) {
            iPostRepository.save(postMapper.mapEditToDtoById(postRequest, post));
        } else if (postName.isPresent() && postName.get().getTitle().equals(postRequest.getTitle()) && postName.get().getId().equals(postRequest.getId())) {
            iPostRepository.save(postMapper.mapEditToDtoById(postRequest, post));
        } else {
            throw new SpringException("Tiêu đề bài viết đã tồn tại");
        }
    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        Post post = iPostRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại bài viết ID - " + id));
        return postMapper.mapToDto(post);
    }

}
