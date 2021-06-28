package com.booking.homestay.employee.mapper;

import com.booking.homestay.admin.dto.UtilityResponse;
import com.booking.homestay.employee.dto.BookingHistoryResponse;
import com.booking.homestay.employee.dto.BookingRequest;
import com.booking.homestay.employee.dto.BookingResponse;
import com.booking.homestay.employee.service.BookingHistoryService;
import com.booking.homestay.model.Booking;
import com.booking.homestay.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BookingMapper {

    @Autowired
    private BookingHistoryService bookingHistoryService;

    @InheritInverseConfiguration
    @Mapping(target = "id", expression = "java(getID())")
    @Mapping(target = "fullName", source = "bookingRequest.fullName")
    @Mapping(target = "address", source = "bookingRequest.address")
    @Mapping(target = "email", source = "bookingRequest.email")
    @Mapping(target = "phone", source = "bookingRequest.phone")
    @Mapping(target = "dateIn", source = "bookingRequest.dateIn")
    @Mapping(target = "dateOut", source = "bookingRequest.dateOut")
    @Mapping(target = "identityCard", ignore = true)
    @Mapping(target = "price", source = "bookingRequest.price")
    @Mapping(target = "costsIncurred", constant = "0")
    @Mapping(target = "discount", constant = "0")
    @Mapping(target = "depositPrice", expression = "java(getDepositPrice(bookingRequest))")
    @Mapping(target = "deposit", source = "bookingRequest.deposit")
    @Mapping(target = "description", source = "bookingRequest.description")
    @Mapping(target = "status", constant = "Not")
    @Mapping(target = "user.id", source = "bookingRequest.id_user")
    @Mapping(target = "creator.id", source = "user.id")
    @Mapping(target = "house.id", source = "bookingRequest.id_house")
    @Mapping(target = "createDate", expression = "java(java.time.Instant.now())")
    public abstract Booking map(BookingRequest bookingRequest, User user);


    long getID() {
        double randomDouble = Math.random();
        randomDouble = randomDouble * 100000000 + 1;
        int randomInt = (int) randomDouble;
        return randomInt;
    }

    Integer getDepositPrice(BookingRequest bookingRequest) {
        if (bookingRequest.isDeposit() == true) {
            return 0;
        } else {
            int price = bookingRequest.getPrice();
            int depositPrice;
            if (price >= 2000000) {
                depositPrice = (int) (price * 0.25);
            } else {
                depositPrice = (int) (price * 0.5);
            }
            return depositPrice;
        }
    }


    @InheritInverseConfiguration
    @Mapping(target = "id", expression = "java(getID())")
    @Mapping(target = "fullName", source = "bookingRequest.fullName")
    @Mapping(target = "address", source = "bookingRequest.address")
    @Mapping(target = "email", source = "bookingRequest.email")
    @Mapping(target = "phone", source = "bookingRequest.phone")
    @Mapping(target = "dateIn", source = "bookingRequest.dateIn")
    @Mapping(target = "dateOut", source = "bookingRequest.dateOut")
    @Mapping(target = "identityCard", ignore = true)
    @Mapping(target = "price", source = "bookingRequest.price")
    @Mapping(target = "costsIncurred", constant = "0")
    @Mapping(target = "discount", constant = "0")
    @Mapping(target = "depositPrice", expression = "java(getDepositPrice(bookingRequest))")
    @Mapping(target = "deposit", source = "bookingRequest.deposit")
    @Mapping(target = "description", source = "bookingRequest.description")
    @Mapping(target = "status", constant = "Not")
    @Mapping(target = "creator.id", source = "user.id")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "house.id", source = "bookingRequest.id_house")
    @Mapping(target = "createDate", expression = "java(java.time.Instant.now())")
    public abstract Booking mapNotId(BookingRequest bookingRequest, User user);

    @InheritInverseConfiguration
    @Mapping(target = "id", expression = "java(getID())")
    @Mapping(target = "fullName", source = "bookingRequest.fullName")
    @Mapping(target = "address", source = "bookingRequest.address")
    @Mapping(target = "email", source = "bookingRequest.email")
    @Mapping(target = "phone", source = "bookingRequest.phone")
    @Mapping(target = "dateIn", source = "bookingRequest.dateIn")
    @Mapping(target = "dateOut", source = "bookingRequest.dateOut")
    @Mapping(target = "identityCard", ignore = true)
    @Mapping(target = "price", source = "bookingRequest.price")
    @Mapping(target = "costsIncurred", constant = "0")
    @Mapping(target = "discount", constant = "0")
    @Mapping(target = "depositPrice", expression = "java(getDepositPrice(bookingRequest))")
    @Mapping(target = "deposit", source = "bookingRequest.deposit")
    @Mapping(target = "description", source = "bookingRequest.description")
    @Mapping(target = "status", constant = "Not")
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "user.id", source = "user.id")
    @Mapping(target = "house.id", source = "bookingRequest.id_house")
    @Mapping(target = "createDate", expression = "java(java.time.Instant.now())")
    public abstract Booking mapNotCreator(BookingRequest bookingRequest, User user);

    @InheritInverseConfiguration
    @Mapping(target = "id", expression = "java(getID())")
    @Mapping(target = "fullName", source = "bookingRequest.fullName")
    @Mapping(target = "address", source = "bookingRequest.address")
    @Mapping(target = "email", source = "bookingRequest.email")
    @Mapping(target = "phone", source = "bookingRequest.phone")
    @Mapping(target = "dateIn", source = "bookingRequest.dateIn")
    @Mapping(target = "dateOut", source = "bookingRequest.dateOut")
    @Mapping(target = "identityCard", ignore = true)
    @Mapping(target = "price", source = "bookingRequest.price")
    @Mapping(target = "costsIncurred", constant = "0")
    @Mapping(target = "discount", constant = "0")
    @Mapping(target = "depositPrice", expression = "java(getDepositPrice(bookingRequest))")
    @Mapping(target = "deposit", source = "bookingRequest.deposit")
    @Mapping(target = "description", source = "bookingRequest.description")
    @Mapping(target = "status", constant = "Not")
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "house.id", source = "bookingRequest.id_house")
    @Mapping(target = "createDate", expression = "java(java.time.Instant.now())")
    public abstract Booking mapNotCreatorNotId(BookingRequest bookingRequest);


    @Mapping(target = "id", source = "booking.id")
    @Mapping(target = "fullName", source = "booking.fullName")
    @Mapping(target = "address", source = "booking.address")
    @Mapping(target = "email", source = "booking.email")
    @Mapping(target = "phone", source = "booking.phone")
    @Mapping(target = "dateIn", source = "booking.dateIn")
    @Mapping(target = "dateOut", source = "booking.dateOut")
    @Mapping(target = "identityCard", source = "booking.identityCard")
    @Mapping(target = "price", source = "booking.price")
    @Mapping(target = "costsIncurred", source = "booking.costsIncurred")
    @Mapping(target = "discount", source = "booking.discount")
    @Mapping(target = "depositPrice", source = "booking.depositPrice")
    @Mapping(target = "status", source = "booking.status")
    @Mapping(target = "deposit", source = "booking.deposit")
    @Mapping(target = "description", source = "booking.description")
    @Mapping(target = "userName", source = "booking.user.userName")
    @Mapping(target = "creatorName", source = "booking.creator.userName")
    @Mapping(target = "houseName", source = "booking.house.houseName")
    @Mapping(target = "homestayName", source = "booking.house.homeStay.homeStayName")
    @Mapping(target = "id_house", source = "booking.house.id")
    @Mapping(target = "createDate", source = "createDate")
    @Mapping(target = "bookingHistoryResponses", expression = "java(getAllBookingHistory(booking.getId()))")
    public abstract BookingResponse mapToDto(Booking booking);


    List<BookingHistoryResponse> getAllBookingHistory(Long id) {
        return bookingHistoryService.getAllBookingHistory(id);
    }

    @Mapping(target = "id", source = "bookingRequest.id")
    @Mapping(target = "fullName", source = "bookingRequest.fullName")
    @Mapping(target = "address", source = "bookingRequest.address")
    @Mapping(target = "email", source = "bookingRequest.email")
    @Mapping(target = "phone", source = "bookingRequest.phone")
    @Mapping(target = "dateIn", source = "bookingRequest.dateIn")
    @Mapping(target = "dateOut", source = "bookingRequest.dateOut")
    @Mapping(target = "identityCard", source = "bookingRequest.identityCard")
    @Mapping(target = "price", source = "bookingRequest.price")
    @Mapping(target = "costsIncurred", source = "bookingRequest.costsIncurred")
    @Mapping(target = "discount", source = "bookingRequest.discount")
    @Mapping(target = "status", source = "booking.status")
    @Mapping(target = "depositPrice", source = "booking.depositPrice")
    @Mapping(target = "deposit", source = "booking.deposit")
    @Mapping(target = "description", source = "bookingRequest.description")
    @Mapping(target = "user.id", source = "booking.user.id")
    @Mapping(target = "creator.id", source = "booking.creator.id")
    @Mapping(target = "house.id", source = "bookingRequest.id_house")
    @Mapping(target = "createDate", source = "booking.createDate")
    public abstract Booking mapToEdit(BookingRequest bookingRequest, Booking booking);

}

