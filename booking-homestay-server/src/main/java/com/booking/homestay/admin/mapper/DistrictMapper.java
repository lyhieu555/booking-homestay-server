package com.booking.homestay.admin.mapper;


import com.booking.homestay.admin.dto.DistrictRequest;
import com.booking.homestay.admin.dto.DistrictResponse;
import com.booking.homestay.model.City;
import com.booking.homestay.model.District;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class DistrictMapper {


    @Mapping(target = "id",  ignore = true )
    @Mapping(target = "districtName", source = "districtRequest.districtName" )
    @Mapping(target = "type", source = "districtRequest.type" )
    @Mapping(target = "city.id", source = "city.id" )
    public abstract District map(DistrictRequest districtRequest, City city);


    @Mapping(target = "id",  source = "district.id" )
    @Mapping(target = "districtName", source = "district.districtName" )
    @Mapping(target = "type", source = "district.type" )
    @Mapping(target = "cityName", source =  "district.city.cityName")
    public abstract DistrictResponse mapToDto(District district);


    @Mapping(target = "id",  source = "districtRequest.id"  )
    @Mapping(target = "districtName", source = "districtRequest.districtName" )
    @Mapping(target = "type", source = "districtRequest.type" )
    @Mapping(target = "city.id", source = "city.id" )
    public abstract District mapEditToDtoById(DistrictRequest districtRequest, City city);


}
