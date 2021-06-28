package com.booking.homestay.employee.mapper;

import com.booking.homestay.employee.dto.HouseRequest;
import com.booking.homestay.employee.dto.HouseResponse;
import com.booking.homestay.member.dto.FeedbackResponse;
import com.booking.homestay.member.service.FeedbackService;
import com.booking.homestay.model.House;
import com.booking.homestay.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class HouseMapper {

    @Autowired
    private FeedbackService feedbackService;

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "houseName", source = "houseRequest.houseName")
    @Mapping(target = "amountRoom", source = "houseRequest.amountRoom")
    @Mapping(target = "price", source = "houseRequest.price")
    @Mapping(target = "size", source = "houseRequest.size")
    @Mapping(target = "capacity", source = "houseRequest.capacity")
    @Mapping(target = "image", source = "houseRequest.image")
    @Mapping(target = "description", source = "houseRequest.description")
    @Mapping(target = "status", constant = "true")
    @Mapping(target = "homeStay", source = "user.homeStay")
    public abstract House mapToSave(HouseRequest houseRequest, User user);


    @Mapping(target = "id", source = "house.id")
    @Mapping(target = "houseName", source = "house.houseName")
    @Mapping(target = "amountRoom", source = "house.amountRoom")
    @Mapping(target = "price", source = "house.price")
    @Mapping(target = "size", source = "house.size")
    @Mapping(target = "capacity", source = "house.capacity")
    @Mapping(target = "image", source = "house.image")
    @Mapping(target = "description", source = "house.description")
    @Mapping(target = "status", source = "house.status")
    @Mapping(target = "id_homeStay", source = "house.homeStay.id")
    @Mapping(target = "feedbackResponses", expression = "java(getAllFeedBack(house.getId()))")
    @Mapping(target = "star", expression = "java(getStar(house.getId()))")
    @Mapping(target = "scores", expression = "java(getScores(house.getId()))")
    public abstract HouseResponse mapToDto(House house);


    Double getScores(Long id) {
        double total = 0;
        int dem = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        double totalScores;
        List<FeedbackResponse> feedbackResponse = feedbackService.getAllFeedBackByHouse(id);
        for (FeedbackResponse feedbackResponse1 : feedbackResponse) {
            total += feedbackResponse1.getRate();
            dem++;
        }
        if (dem == 0) {
            return 0.0;
        } else {
            totalScores = total / dem;
           double  roundDbl = Math.round(totalScores*100.0)/100.0;
            return roundDbl;
        }
    }

    Integer getStar(Long id) {
        int total = 0;
        int dem = 0;
        int totalScores;
        List<FeedbackResponse> feedbackResponse = feedbackService.getAllFeedBackByHouse(id);
        for (FeedbackResponse feedbackResponse1 : feedbackResponse) {
            total += feedbackResponse1.getRate();
            dem++;
        }
        if (dem == 0) {
            return 0;
        } else {
            totalScores = total / dem;
            return totalScores;
        }
    }

    List<FeedbackResponse> getAllFeedBack(Long id) {
        return feedbackService.getAllFeedBackByHouse(id);
    }

    @Mapping(target = "id", source = "houseRequest.id")
    @Mapping(target = "houseName", source = "houseRequest.houseName")
    @Mapping(target = "status", source = "house.status")
    @Mapping(target = "size", source = "houseRequest.size")
    @Mapping(target = "amountRoom", source = "houseRequest.amountRoom")
    @Mapping(target = "price", source = "houseRequest.price")
    @Mapping(target = "capacity", source = "houseRequest.capacity")
    @Mapping(target = "image", source = "houseRequest.image")
    @Mapping(target = "description", source = "houseRequest.description")
    @Mapping(target = "homeStay", source = "house.homeStay")
    public abstract House mapToEdit(HouseRequest houseRequest, House house);

}
