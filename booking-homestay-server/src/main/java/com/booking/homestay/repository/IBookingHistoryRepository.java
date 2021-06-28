package com.booking.homestay.repository;

import com.booking.homestay.model.BookingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookingHistoryRepository extends JpaRepository<BookingHistory, Long> {
    List<BookingHistory> findByBookingId (Long id);
}
