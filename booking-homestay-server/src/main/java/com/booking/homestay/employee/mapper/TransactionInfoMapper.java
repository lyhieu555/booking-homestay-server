package com.booking.homestay.employee.mapper;

import com.booking.homestay.employee.dto.BookingResponse;
import com.booking.homestay.employee.dto.TransactionInfoResponse;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.Booking;
import com.booking.homestay.model.TransactionInfo;
import com.booking.homestay.model.User;
import com.booking.homestay.repository.IBookingRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Mapper(componentModel = "spring")
public abstract class TransactionInfoMapper {

    @Autowired
    private IBookingRepository iBookingRepository;

    @Autowired
    private  BookingMapper bookingMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateRelease", expression = "java(java.time.Instant.now())")
    @Mapping(target = "totalPrice", expression = "java(getTotal(booking))")
    @Mapping(target = "booking.id", source = "booking.id")
    @Mapping(target = "user", source = "user")
    public abstract TransactionInfo mapToSave(Booking booking, User user);

    @Mapping(target = "id", source = "transactionInfo.id")
    @Mapping(target = "dateRelease", source = "transactionInfo.dateRelease")
    @Mapping(target = "totalPrice", source = "transactionInfo.totalPrice")
    @Mapping(target = "creatorName", source = "transactionInfo.user.userName")
    @Mapping(target = "bookingResponse", expression = "java(getBookingById(transactionInfo.getBooking().getId()))")
    public abstract TransactionInfoResponse mapToDto(TransactionInfo transactionInfo);

    @Transactional(readOnly = true)
    public BookingResponse getBookingById(Long id) {
        Booking booking = iBookingRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại đơn đặt nhà ở với mã ID: " + id));
        return bookingMapper.mapToDto(booking);
    }

    int getTotal(Booking booking) {
        int total = booking.getPrice() - booking.getDepositPrice() - booking.getDiscount() + booking.getCostsIncurred();
        return total;
    }

}
