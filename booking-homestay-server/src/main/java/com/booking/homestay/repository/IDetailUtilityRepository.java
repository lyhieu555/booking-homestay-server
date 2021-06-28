package com.booking.homestay.repository;

import com.booking.homestay.model.DetailUtility;
import com.booking.homestay.model.House;
import com.booking.homestay.model.Utility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDetailUtilityRepository extends JpaRepository<DetailUtility, Long> {

    Optional<List<DetailUtility>> findByUtility_Id(Long id);

    List<DetailUtility> findByHouse_Id(Long id);

    void deleteByHouse_Id(Long id);

    List<DetailUtility> findByUtilityAndHouse(Utility utility, House house);



}
