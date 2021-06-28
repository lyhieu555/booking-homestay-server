package com.booking.homestay.repository;

import com.booking.homestay.model.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IViewRepository extends JpaRepository<View, Long> {

    Optional<View> findByViewName(String name);
}
