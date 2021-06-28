package com.booking.homestay.repository;


import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IHouseRepository extends JpaRepository<House, Long> {

    List<House> findByHouseNameAndHomeStay(String name, HomeStay homeStay);

    List<House> findByHomeStayAndStatusTrue(HomeStay homeStay);

    List<House> findByHomeStay(HomeStay homeStay);

    List<House> findByHomeStay_IdAndStatusTrue(Long id);

    List<House> findHouseByStatusTrue();

}
