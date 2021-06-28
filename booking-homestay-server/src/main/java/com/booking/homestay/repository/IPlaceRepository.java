package com.booking.homestay.repository;

import com.booking.homestay.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findByPlaceName(String name);

}

