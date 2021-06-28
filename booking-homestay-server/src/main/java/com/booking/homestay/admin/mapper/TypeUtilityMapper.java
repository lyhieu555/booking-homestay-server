package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.TypeUtilityRequest;
import com.booking.homestay.admin.dto.TypeUtilityResponse;
import com.booking.homestay.admin.dto.UtilityResponse;
import com.booking.homestay.admin.service.UtilityService;
import com.booking.homestay.model.TypeUtility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TypeUtilityMapper {

    @Autowired
    private UtilityService utilityService;

    @Mapping(target = "id",  ignore = true )
    @Mapping(target = "typeName", source = "typeUtilityRequest.typeName" )
    public abstract TypeUtility map(TypeUtilityRequest typeUtilityRequest);


    @Mapping(target = "id",   source = "typeUtility.id" )
    @Mapping(target = "typeName", source = "typeUtility.typeName" )
    @Mapping(target = "utility", expression = "java(getAllUtility(typeUtility.getId()))" )
    public abstract TypeUtilityResponse mapToDto(TypeUtility typeUtility);

    List<UtilityResponse> getAllUtility(Long id) {
        return utilityService.getUtilityByType(id);
    }

    @Mapping(target = "id",  source = "typeUtilityRequest.id"  )
    @Mapping(target = "typeName", source = "typeUtilityRequest.typeName" )
    public abstract TypeUtility mapEditToDtoById(TypeUtilityRequest typeUtilityRequest, TypeUtility typeUtility);

}
