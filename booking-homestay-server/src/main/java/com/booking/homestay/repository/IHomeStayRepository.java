package com.booking.homestay.repository;


import com.booking.homestay.model.HomeStay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IHomeStayRepository extends JpaRepository<HomeStay, Long> {

    Optional<HomeStay> findByHomeStayName(String name);

    Optional<List<HomeStay>> findByVillage_Id(Long id);

    @Query(value = "SELECT u FROM HomeStay u WHERE  u.status = true ")
    List<HomeStay> findByHomeStayNotLock();

    @Query(value = "SELECT u FROM HomeStay u WHERE  u.status = false ")
    List<HomeStay> findByHomeStayLock();

}
