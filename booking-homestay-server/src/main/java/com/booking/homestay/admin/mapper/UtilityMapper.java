package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.UtilityRequest;
import com.booking.homestay.admin.dto.UtilityResponse;
import com.booking.homestay.model.TypeUtility;
import com.booking.homestay.model.Utility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UtilityMapper {

    @Mapping(target = "id",  ignore = true )
    @Mapping(target = "utilityName", source = "utilityRequest.utilityName" )
    @Mapping(target = "image", source = "utilityRequest.image" )
    @Mapping(target = "typeUtility.id", source = "utilityRequest.id_typeUtility")
    public abstract Utility map(UtilityRequest utilityRequest);


    @Mapping(target = "id", source = "utility.id" )
    @Mapping(target = "utilityName", source = "utility.utilityName" )
    @Mapping(target = "image", source = "utility.image" )
    @Mapping(target = "id_typeUtility",source = "utility.typeUtility.id")
    public abstract UtilityResponse mapToDto(Utility utility);


    @Mapping(target = "id", source = "utilityRequest.id" )
    @Mapping(target = "utilityName", source = "utilityRequest.utilityName" )
    @Mapping(target = "image", source = "utilityRequest.image" )
    @Mapping(target = "typeUtility.id", source = "utilityRequest.id_typeUtility")
    public abstract Utility mapEditToDtoById(UtilityRequest utilityRequest, Utility utility);

}
