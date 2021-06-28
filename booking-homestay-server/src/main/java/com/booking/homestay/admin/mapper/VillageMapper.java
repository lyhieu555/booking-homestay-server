package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.VillageRequest;
import com.booking.homestay.admin.dto.VillageResponse;
import com.booking.homestay.model.City;
import com.booking.homestay.model.District;
import com.booking.homestay.model.Village;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class VillageMapper {

    @Mapping(target = "id",  ignore = true )
    @Mapping(target = "villageName", source = "villageRequest.villageName" )
    @Mapping(target = "type", source = "villageRequest.type" )
    @Mapping(target = "district.id", source = "district.id" )
    public abstract Village map(VillageRequest villageRequest, District district);


    @Mapping(target = "id",  source = "village.id" )
    @Mapping(target = "villageName", source = "village.villageName" )
    @Mapping(target = "type", source = "village.type" )
    @Mapping(target = "districtName", source = "village.district.districtName")
    @Mapping(target = "cityName", source = "village.district.city.cityName")
    public abstract VillageResponse mapToDto(Village village);


    @Mapping(target = "id",  source = "villageRequest.id"  )
    @Mapping(target = "villageName", source = "villageRequest.villageName" )
    @Mapping(target = "type", source = "villageRequest.type" )
    @Mapping(target = "district.id", source = "district.id" )
    public abstract Village mapEditToDtoById(VillageRequest villageRequest, District district);

}
