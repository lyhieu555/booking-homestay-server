package com.booking.homestay.admin.service;

import com.booking.homestay.admin.dto.statistics.AccountResponse;
import com.booking.homestay.admin.dto.statistics.DateRequest;
import com.booking.homestay.model.Booking;
import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.TransactionInfo;
import com.booking.homestay.model.User;
import com.booking.homestay.repository.IBookingRepository;
import com.booking.homestay.repository.IHomeStayRepository;
import com.booking.homestay.repository.ITransactionInfoRepository;
import com.booking.homestay.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@Transactional
public class StatisticsService {

    private final IUserRepository userRepository;
    private final IBookingRepository iBookingRepository;
    private final ITransactionInfoRepository iTransactionInfoRepository;
    private final IHomeStayRepository iHomeStayRepository;

    public List<AccountResponse> getAllAccount() {
        List<AccountResponse> list = new ArrayList<>();
        List<User> listEmployeeLock = userRepository.findByEmployeeLock();
        List<User> listEmployee = userRepository.findByEmployeeNotLock();
        List<User> listMemberLock = userRepository.findByMemberLock();
        List<User> listMember = userRepository.findByMemberNotLock();
        if (listEmployeeLock.size() != 0) {
            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setValue((long) listEmployeeLock.size());
            accountResponse.setName("Nhân viên bị khóa");
            list.add(accountResponse);
        }
        if (listEmployee.size() != 0) {
            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setValue((long) listEmployee.size());
            accountResponse.setName("Nhân viên");
            list.add(accountResponse);
        }
        if (listMemberLock.size() != 0) {
            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setValue((long) listMemberLock.size());
            accountResponse.setName("Khách hàng bị khóa");
            list.add(accountResponse);
        }
        if (listMember.size() != 0) {
            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setValue((long) listMember.size());
            accountResponse.setName("Khách hàng");
            list.add(accountResponse);
        }
        return list;
    }

    public List<AccountResponse> getAllHomeStay(DateRequest dateRequest) {
        List<AccountResponse> list = new ArrayList<>();
        List<HomeStay> homeStays = iHomeStayRepository.findAll();
        for (HomeStay homeStay : homeStays) {
            if(dateRequest.getLastDay() != null && dateRequest.getFirstDay() != null){
                List<Booking> bookings = iBookingRepository.findByBookingByHomeStayDate(homeStay, dateRequest.getFirstDay().toInstant(), dateRequest.getLastDay().toInstant());
                if (bookings.size() != 0) {
                    int total = 0;
                    for (Booking booking : bookings) {
                        if (booking.getStatus().equals("Cancel")) {
                            total += booking.getDepositPrice();
                        } else {
                            TransactionInfo transactionInfo = iTransactionInfoRepository.findByBooking(booking);
                            total += transactionInfo.getTotalPrice();
                        }
                    }
                    AccountResponse accountResponse = new AccountResponse();
                    accountResponse.setValue((long) total);
                    accountResponse.setName(homeStay.getHomeStayName());
                    list.add(accountResponse);
                }
            }else {
                List<Booking> bookings = iBookingRepository.findByBookingByHomeStay(homeStay);
                if (bookings.size() != 0) {
                    int total = 0;
                    for (Booking booking : bookings) {
                        if (booking.getStatus().equals("Cancel")) {
                            total += booking.getDepositPrice();
                        } else {
                            TransactionInfo transactionInfo = iTransactionInfoRepository.findByBooking(booking);
                            total += transactionInfo.getTotalPrice();
                        }
                    }
                    AccountResponse accountResponse = new AccountResponse();
                    accountResponse.setValue((long) total);
                    accountResponse.setName(homeStay.getHomeStayName());
                    list.add(accountResponse);
                }
            }
        }
        return list;
    }
}
