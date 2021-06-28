package com.booking.homestay.repository;

import com.booking.homestay.model.TypePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITypePostRepository extends JpaRepository<TypePost, Long> {

    Optional<TypePost> findByTypeName(String name);

}
