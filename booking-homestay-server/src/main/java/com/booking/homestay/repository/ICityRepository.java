package com.booking.homestay.repository;

import com.booking.homestay.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {

    Optional<City> findByCityName(String cityName);

}
