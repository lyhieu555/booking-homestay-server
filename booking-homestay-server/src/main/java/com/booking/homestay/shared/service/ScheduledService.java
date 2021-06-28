package com.booking.homestay.shared.service;

import com.booking.homestay.model.Booking;
import com.booking.homestay.model.VerificationTokenAccount;
import com.booking.homestay.model.VerificationTokenPassword;
import com.booking.homestay.repository.IBookingRepository;
import com.booking.homestay.repository.IUserRepository;
import com.booking.homestay.repository.IVerificationTokenAccountRepository;
import com.booking.homestay.repository.IVerificationTokenPasswordRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ScheduledService {

    private final IBookingRepository iBookingRepository;
    private final IVerificationTokenAccountRepository iVerificationTokenAccountRepository;
    private final IVerificationTokenPasswordRepository iVerificationTokenPasswordRepository;
    private final IUserRepository iUserRepository;


    @Scheduled(fixedDelay = 43200000)  //6 tiếng lọc một lần
    public void TokenCleaning() {
        try {
            Instant time = Instant.now();
            System.out.println("Lọc token password " + time);
            List<VerificationTokenPassword> tokenPasswordList = iVerificationTokenPasswordRepository.findAll();
            if (tokenPasswordList.size() != 0) {
                for (VerificationTokenPassword verificationTokenPassword : tokenPasswordList) {
                    if (time.isAfter(verificationTokenPassword.getExpiryDate())) {
                        iVerificationTokenPasswordRepository.deleteById(verificationTokenPassword.getId());
                    }
                }
            }
            List<VerificationTokenAccount> tokenAccountList = iVerificationTokenAccountRepository.findAll();
            if (tokenAccountList.size() != 0) {
                for (VerificationTokenAccount verificationTokenAccount : tokenAccountList) {
                    if (time.isAfter(verificationTokenAccount.getExpiryDate())) {
                        iVerificationTokenAccountRepository.deleteById(verificationTokenAccount.getId());
                        iUserRepository.deleteById(verificationTokenAccount.getUser().getId());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Lọc token bị lỗi " + e);
        }
    }

    @Scheduled(fixedDelay = 600000) //10 phút lọc một lần
    public void BookingCleaning() {
        try {
            Instant time = Instant.now();
            System.out.println("Lọc booking " + time);
            List<Booking> bookingsList = iBookingRepository.findByDepositIsFalse();
            if (bookingsList.size() != 0) {
                for (Booking booking : bookingsList) {
                    if (time.isAfter(booking.getCreateDate().plusMillis(7200000))) {
                        iBookingRepository.deleteById(booking.getId());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Lọc booking bị lỗi " + e);
        }
    }
}
