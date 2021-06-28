package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.CityRequest;
import com.booking.homestay.admin.dto.CityResponse;
import com.booking.homestay.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CityMapper {

    @Mapping(target = "id",  ignore = true )
    @Mapping(target = "cityName", source = "cityRequest.cityName" )
    @Mapping(target = "type", source = "cityRequest.type" )
    public abstract City map(CityRequest cityRequest);


    @Mapping(target = "id",  source = "city.id")
    @Mapping(target = "cityName", source = "city.cityName" )
    @Mapping(target = "type", source = "city.type" )
    public abstract CityResponse mapToDto(City city);


    @Mapping(target = "id",  source = "cityRequest.id" )
    @Mapping(target = "cityName", source = "cityRequest.cityName" )
    @Mapping(target = "type", source = "cityRequest.type" )
    public abstract City mapEditToDtoById(CityRequest cityRequest);


}
