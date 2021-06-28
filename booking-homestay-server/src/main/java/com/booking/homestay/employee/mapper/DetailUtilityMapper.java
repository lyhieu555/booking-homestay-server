package com.booking.homestay.employee.mapper;

import com.booking.homestay.employee.dto.DetailUtilityRequest;
import com.booking.homestay.employee.dto.DetailUtilityResponse;
import com.booking.homestay.model.DetailUtility;
import com.booking.homestay.model.House;
import com.booking.homestay.model.Utility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class DetailUtilityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "utility", source = "utility")
    @Mapping(target = "house", source = "house")
    @Mapping(target = "quantity", constant = "1")
    public abstract DetailUtility mapToSave(House house, Utility utility);

    @Mapping(target = "id", source = "detailUtility.id")
    @Mapping(target = "id_house", source = "detailUtility.house.id")
    @Mapping(target = "quantity", source = "detailUtility.quantity")
    @Mapping(target = "utilityName", source = "detailUtility.utility.utilityName")
    @Mapping(target = "image", source = "detailUtility.utility.image")
    @Mapping(target = "id_utility", source = "detailUtility.utility.id")
    public abstract DetailUtilityResponse mapToDto(DetailUtility detailUtility);

    @Mapping(target = "id", source = "detailUtilityRequest.id")
    @Mapping(target = "utility", source = "detailUtility.utility")
    @Mapping(target = "house", source = "detailUtility.house")
    @Mapping(target = "quantity", source = "detailUtilityRequest.quantity")
    public abstract DetailUtility mapToEdit(DetailUtility detailUtility, DetailUtilityRequest detailUtilityRequest);

}
