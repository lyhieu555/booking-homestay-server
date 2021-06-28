package com.booking.homestay.repository;

import com.booking.homestay.model.Booking;
import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.TransactionInfo;
import com.booking.homestay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITransactionInfoRepository extends JpaRepository<TransactionInfo, Long> {

    @Query(value = "SELECT t FROM TransactionInfo t WHERE t.booking.house.homeStay = ?1")
    List<TransactionInfo> findByHouseHomestay(HomeStay homeStay);

    @Query(value = "SELECT u FROM TransactionInfo u WHERE u.booking.house.id = ?1 and u.booking.user = ?2 ")
    List<TransactionInfo> findByHouseAndUser(Long id, User user);


    TransactionInfo findByBooking(Booking booking);
}
