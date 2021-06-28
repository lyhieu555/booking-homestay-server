package com.booking.homestay.repository;

import com.booking.homestay.model.City;
import com.booking.homestay.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDistrictRepository extends JpaRepository<District, Long> {

    List<District> findByCity_Id(Long id);

    List<District> findByCity_CityName(String cityName);

    List<District> findByDistrictNameAndCity_Id(String name, Long cityId);

}
