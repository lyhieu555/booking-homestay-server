package com.booking.homestay.repository;

import com.booking.homestay.employee.dto.BookingRequest;
import com.booking.homestay.model.Booking;
import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT u FROM Booking u WHERE u.house.homeStay = ?1 and u.status = 'Not' ")
    List<Booking> findByHouseHomeStayAndStatusNot(HomeStay homeStay);

    @Query(value = "SELECT u FROM Booking u WHERE u.house.homeStay = ?1 and u.status = 'CheckIn' ")
    List<Booking> findByHouseHomeStayAndStatusCheckIn(HomeStay homeStay);

    @Query(value = "SELECT u FROM Booking u WHERE u.house.homeStay = ?1 and u.status = 'CheckOut' ")
    List<Booking> findByHouseHomeStayAndStatusCheckOut(HomeStay homeStay);

    @Query(value = "SELECT u FROM Booking u WHERE u.house.homeStay = ?1 and u.status = 'Processing' or u.house.homeStay = ?1 and u.status = 'Refunded' or u.house.homeStay = ?1 and u.status = 'Cancel' ")
    List<Booking> findByHouseHomeStayAndStatusCancellation(HomeStay homeStay);

    @Query(value = "SELECT u FROM Booking u WHERE u.status = 'Processing' or u.status = 'Refunded' or u.status = 'Cancel' ")
    List<Booking> findByStatusCancellation();

    @Query(value = "SELECT u FROM Booking u WHERE u.house.id = ?1 and u.status = 'CheckIn' or u.house.id = ?1 and u.status = 'Not' ")
    List<Booking> findByHouseId(Long id);

    List<Booking> findByHouse_Id(Long id);

    List<Booking> findByDepositIsFalse();

    @Query(value = "SELECT u FROM Booking u WHERE u.user = ?1")
    List<Booking> findByMember(User user);

    @Query(value = "SELECT u FROM Booking u WHERE u.phone = ?1 and u.id = ?2")
    List<Booking> seachBooking(String phone, Long idBook);

    @Query(value = "SELECT u FROM Booking u WHERE u.house.homeStay = ?1 and u.status = 'CheckOut' or  u.house.homeStay = ?1 and u.status = 'Cancel'")
    List<Booking> findByBookingByHomeStay(HomeStay homeStay);

    @Query(value = "SELECT u FROM Booking u WHERE u.house.homeStay = ?1 and u.status = 'CheckOut' and u.createDate >= ?2 and u.createDate <= ?3 or  u.house.homeStay = ?1 and u.status = 'Cancel' and u.createDate >= ?2 and u.createDate <= ?3" )
    List<Booking> findByBookingByHomeStayDate(HomeStay homeStay, Instant firsDay, Instant lastDay);
}
