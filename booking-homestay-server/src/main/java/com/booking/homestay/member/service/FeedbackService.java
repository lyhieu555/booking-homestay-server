package com.booking.homestay.member.service;

import com.booking.homestay.exception.SpringException;
import com.booking.homestay.member.dto.FeedbackRequest;
import com.booking.homestay.member.dto.FeedbackResponse;
import com.booking.homestay.member.mapper.FeedbackMapper;
import com.booking.homestay.model.FeedBack;
import com.booking.homestay.model.TransactionInfo;
import com.booking.homestay.repository.IFeedBackRepository;
import com.booking.homestay.repository.ITransactionInfoRepository;
import com.booking.homestay.shared.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class FeedbackService {

    private final IFeedBackRepository iFeedBackRepository;
    private final ITransactionInfoRepository iTransactionInfoRepository;
    private final FeedbackMapper feedbackMapper;
    private final AuthService authService;

    public void save(FeedbackRequest feedbackRequest) {
        List<FeedBack> feedBack = iFeedBackRepository.findByHouse_IdAndUser(feedbackRequest.getId_house(), authService.getCurrentUser());
        if (feedBack.size() != 0) {
            throw new SpringException("Bạn đã đánh giá căn nhà này");
        }
        List<TransactionInfo> transactionInfos = iTransactionInfoRepository.findByHouseAndUser(feedbackRequest.getId_house(), authService.getCurrentUser());
        if (transactionInfos.size() == 0) {
            throw new SpringException("Bạn chưa từng sử dụng nhà này");
        }
        iFeedBackRepository.save(feedbackMapper.mapToSave(feedbackRequest, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public List<FeedbackResponse> getAllFeedBackByHouse(Long id) {
        return iFeedBackRepository.findByHouse_Id(id)
                .stream()
                .map(feedbackMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<FeedbackResponse> getAllFeedBack() {
        return iFeedBackRepository.findAll()
                .stream()
                .map(feedbackMapper::mapToDto)
                .collect(toList());
    }
}
