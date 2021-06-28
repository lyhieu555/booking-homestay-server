package com.booking.homestay.repository;

import com.booking.homestay.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDetailPlaceRepository extends JpaRepository<DetailPlace, Long> {

    Optional<List<DetailPlace>> findByPlace_Id(Long id);

    List<DetailPlace> findByHomeStay_Id(Long id);


    List<DetailPlace> findByPlaceAndHomeStay(Place place, HomeStay homeStay);

}
