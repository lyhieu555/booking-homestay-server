package com.booking.homestay.employee.mapper;


import com.booking.homestay.employee.dto.MemberRequest;
import com.booking.homestay.employee.dto.MemberResponse;
import com.booking.homestay.model.User;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public abstract class MemberMapper {

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "userName", source = "user.userName")
    @Mapping(target = "password", source = "user.password")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "address", source = "user.address")
    @Mapping(target = "image", source = "user.image")
    @Mapping(target = "role", source = "user.role")
    @Mapping(target = "createdDate", source = "user.createdDate")
    @Mapping(target = "dateOfBirth", expression = "java(getFormatDate(user))")
    @Mapping(target = "sex", source = "user.sex")
    @Mapping(target = "enabled", source = "user.enabled")
    @Mapping(target = "status", source = "user.status")
    @Mapping(target = "id_creator", source = "user.creator.id")
    public abstract MemberResponse mapToDto(User user);

    String getFormatDate(User user) {
        if (user.getDateOfBirth() != null) {
            DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
            return df3.format(user.getDateOfBirth());
        } else {
            return "";
        }
    }

    @Mapping(target = "id", source = "memberRequest.id")
    @Mapping(target = "userName", source = "user.userName")
    @Mapping(target = "password", source = "user.password")
    @Mapping(target = "email", source = "memberRequest.email")
    @Mapping(target = "firstName", source = "memberRequest.firstName")
    @Mapping(target = "lastName", source = "memberRequest.lastName")
    @Mapping(target = "phone", source = "memberRequest.phone")
    @Mapping(target = "address", source = "memberRequest.address")
    @Mapping(target = "image", source = "user.image")
    @Mapping(target = "role", source = "user.role")
    @Mapping(target = "createdDate", source = "user.createdDate")
    @Mapping(target = "dateOfBirth", source = "memberRequest.dateOfBirth")
    @Mapping(target = "sex", source = "memberRequest.sex")
    @Mapping(target = "enabled", source = "user.enabled")
    @Mapping(target = "status", source = "user.status")
    @Mapping(target = "creator.id", source = "user.creator.id")
    public abstract User mapEditToDtoById(MemberRequest memberRequest, User user);

}
