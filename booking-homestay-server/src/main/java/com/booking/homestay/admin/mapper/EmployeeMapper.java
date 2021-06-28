package com.booking.homestay.admin.mapper;



import com.booking.homestay.admin.dto.EmployeeRequest;
import com.booking.homestay.admin.dto.EmployeeResponse;
import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.User;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    @Mapping(target = "id", source = "user.id" )
    @Mapping(target = "userName", source = "user.userName" )
    @Mapping(target = "password", source = "user.password" )
    @Mapping(target = "email", source = "user.email" )
    @Mapping(target = "firstName", source = "user.firstName" )
    @Mapping(target = "lastName", source = "user.lastName" )
    @Mapping(target = "phone", source = "user.phone" )
    @Mapping(target = "address", source = "user.address" )
    @Mapping(target = "image", source = "user.image" )
    @Mapping(target = "role", source = "user.role" )
    @Mapping(target = "createdDate", source = "user.createdDate" )
    @Mapping(target = "dateOfBirth", expression = "java(getFormatDate(user))" )
    @Mapping(target = "sex", source = "user.sex" )
    @Mapping(target = "enabled", source = "user.enabled" )
    @Mapping(target = "status", source = "user.status" )
    @Mapping(target = "id_creator", source = "user.creator.id" )
    @Mapping(target = "id_homeStay", source = "user.homeStay.id")
    @Mapping(target = "homeStayName", source = "user.homeStay.homeStayName")
    public abstract EmployeeResponse mapToDto(User user);

    String getFormatDate(User user) {
        if (user.getDateOfBirth() != null) {
            DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
            return df3.format(user.getDateOfBirth());
        } else {
            return "";
        }
    }

    @Mapping(target = "id", source = "employeeRequest.id" )
    @Mapping(target = "userName", source = "user.userName" )
    @Mapping(target = "password", source = "user.password" )
    @Mapping(target = "email", source = "employeeRequest.email" )
    @Mapping(target = "firstName", source = "employeeRequest.firstName" )
    @Mapping(target = "lastName", source = "employeeRequest.lastName" )
    @Mapping(target = "phone", source = "employeeRequest.phone" )
    @Mapping(target = "address", source = "employeeRequest.address" )
    @Mapping(target = "image", source = "user.image" )
    @Mapping(target = "role", source = "user.role" )
    @Mapping(target = "createdDate", source = "user.createdDate" )
    @Mapping(target = "dateOfBirth", source = "employeeRequest.dateOfBirth" )
    @Mapping(target = "sex", source = "employeeRequest.sex" )
    @Mapping(target = "enabled", source = "user.enabled" )
    @Mapping(target = "status", source = "user.status" )
    @Mapping(target = "creator.id", source = "user.creator.id" )
    @Mapping(target = "homeStay.id", source = "employeeRequest.id_homeStay")
    public abstract User mapEditToDtoById(EmployeeRequest employeeRequest, User user);


}

