package com.booking.homestay.repository;

import com.booking.homestay.model.District;
import com.booking.homestay.model.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IVillageRepository extends JpaRepository<Village, Long> {

    Optional<List<Village>> findByDistrict_Id(Long id);

    List<Village> findByVillageNameAndDistrict(String name, District district);

}
