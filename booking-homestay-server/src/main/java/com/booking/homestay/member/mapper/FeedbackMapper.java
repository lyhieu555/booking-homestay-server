package com.booking.homestay.member.mapper;

import com.booking.homestay.member.dto.FeedbackRequest;
import com.booking.homestay.member.dto.FeedbackResponse;
import com.booking.homestay.model.FeedBack;
import com.booking.homestay.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class FeedbackMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "content", source = "feedbackRequest.content")
    @Mapping(target = "createDate", expression = "java(java.time.Instant.now())" )
    @Mapping(target = "rate", source = "feedbackRequest.rate")
    @Mapping(target = "user.id", source = "user.id")
    @Mapping(target = "house.id", source = "feedbackRequest.id_house")
    public abstract FeedBack mapToSave(FeedbackRequest feedbackRequest, User user);

    @Mapping(target = "id", source = "feedBack.id")
    @Mapping(target = "content", source = "feedBack.content")
    @Mapping(target = "createDate", source = "feedBack.createDate")
    @Mapping(target = "rate", source = "feedBack.rate")
    @Mapping(target = "userName", source = "feedBack.user.userName")
    @Mapping(target = "image", source = "feedBack.user.image")
    @Mapping(target = "houseName", source = "feedBack.house.houseName")
    @Mapping(target = "id_house", source = "feedBack.user.id")
    @Mapping(target = "id_user", source = "feedBack.house.id")
    public abstract FeedbackResponse mapToDto(FeedBack feedBack);

}
