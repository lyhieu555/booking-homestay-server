package com.booking.homestay.repository;

import com.booking.homestay.model.DetailView;
import com.booking.homestay.model.House;
import com.booking.homestay.model.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDetailViewRepository extends JpaRepository<DetailView, Long> {

    Optional<List<DetailView>> findByView_Id(Long id);

    List<DetailView> findByHouse_Id(Long id);

    void deleteByHouse_Id(Long id);

    List<DetailView> findByHouseAndView(House house, View view);

}
