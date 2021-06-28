package com.booking.homestay.repository;


import com.booking.homestay.model.Utility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUtilityRepositoty extends JpaRepository<Utility, Long>  {

    Optional<Utility> findByUtilityName(String name);

    List<Utility> findByTypeUtility_Id(Long id);

}
