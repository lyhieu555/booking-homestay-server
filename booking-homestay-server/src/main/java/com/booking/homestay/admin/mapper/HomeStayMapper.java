package com.booking.homestay.admin.mapper;


import com.booking.homestay.admin.dto.DetailPlaceResponse;
import com.booking.homestay.admin.dto.HomeStayRequest;
import com.booking.homestay.admin.dto.HomeStayResponse;
import com.booking.homestay.admin.service.DetailPlaceService;
import com.booking.homestay.model.DetailPlace;
import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.Place;
import com.booking.homestay.model.Village;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class HomeStayMapper {

    @Autowired
    private DetailPlaceService detailPlaceService;

    @InheritInverseConfiguration
    @Mapping(target = "id",  ignore = true )
    @Mapping(target = "homeStayName", source = "homeStayRequest.homeStayName" )
    @Mapping(target = "description", source = "homeStayRequest.description" )
    @Mapping(target = "phone", source = "homeStayRequest.phone" )
    @Mapping(target = "image", source = "homeStayRequest.image" )
    @Mapping(target = "status", constant = "true")
    @Mapping(target = "village.id", source = "village.id" )
    public abstract HomeStay map(HomeStayRequest homeStayRequest, Village village);


    @Mapping(target = "id", source = "homeStay.id" )
    @Mapping(target = "homeStayName", source = "homeStay.homeStayName" )
    @Mapping(target = "description", source = "homeStay.description" )
    @Mapping(target = "phone", source = "homeStay.phone" )
    @Mapping(target = "image", source = "homeStay.image" )
    @Mapping(target = "status", source = "homeStay.status" )
    @Mapping(target = "villageName", source = "homeStay.village.villageName")
    @Mapping(target = "cityName", source = "homeStay.village.district.city.cityName")
    @Mapping(target = "districtName", source = "homeStay.village.district.districtName")
    @Mapping(target = "detailPlace", expression = "java(getAllDetailPlace(homeStay.getId()))")
    public abstract HomeStayResponse mapToDto(HomeStay homeStay);

    List<DetailPlaceResponse> getAllDetailPlace(Long id){
       return detailPlaceService.getAllByHomeStay(id);
    }

    @Mapping(target = "id", source = "homeStayRequest.id" )
    @Mapping(target = "homeStayName", source = "homeStayRequest.homeStayName" )
    @Mapping(target = "description", source = "homeStayRequest.description" )
    @Mapping(target = "phone", source = "homeStayRequest.phone" )
    @Mapping(target = "image", source = "homeStayRequest.image" )
    @Mapping(target = "status", source = "homeStay.status" )
    @Mapping(target = "village.id", source = "village.id" )
    public abstract HomeStay mapEditToDtoById(HomeStayRequest homeStayRequest, HomeStay homeStay, Village village);


}
