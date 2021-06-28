package com.booking.homestay.repository;

import com.booking.homestay.model.FeedBack;
import com.booking.homestay.model.RefreshToken;
import com.booking.homestay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFeedBackRepository extends JpaRepository<FeedBack, Long> {

    List<FeedBack> findByHouse_Id(Long id);

    List<FeedBack> findByHouse_IdAndUser(Long id, User user);
}
