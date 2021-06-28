package com.booking.homestay.employee.service;

import com.booking.homestay.employee.dto.TransactionInfoResponse;
import com.booking.homestay.employee.mapper.TransactionInfoMapper;
import com.booking.homestay.model.Booking;
import com.booking.homestay.repository.ITransactionInfoRepository;
import com.booking.homestay.shared.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional
@Service
@AllArgsConstructor
public class TransactionInfoService {
    private final ITransactionInfoRepository iTransactionInfoRepository;
    private final TransactionInfoMapper transactionInfoMapper;
    private final AuthService authService;

    public void save(Booking booking) {
        iTransactionInfoRepository.save(transactionInfoMapper.mapToSave(booking, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public List<TransactionInfoResponse> getAllByHouseHomestay() {
        if (authService.getCurrentUser().getHomeStay() == null) {
            return iTransactionInfoRepository.findAll()
                    .stream()
                    .map(transactionInfoMapper::mapToDto)
                    .collect(toList());
        } else {
            return iTransactionInfoRepository.findByHouseHomestay(authService.getCurrentUser().getHomeStay())
                    .stream()
                    .map(transactionInfoMapper::mapToDto)
                    .collect(toList());
        }
    }
}
