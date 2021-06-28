package com.booking.homestay.repository;


import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTypePost_Id(Long id);

    Optional<Post> findByTitle(String name);

    List<Post> findByTypePost_IdAndHomeStayOrTypePost_IdAndHomeStayNull(Long id, HomeStay homeStay, Long typeId);

}
