package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.DetailPlaceResponse;
import com.booking.homestay.employee.dto.DetailViewResponse;
import com.booking.homestay.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class DetailPlaceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "place", source = "place")
    @Mapping(target = "homeStay", source = "homeStay")
    public abstract DetailPlace mapToSave(Place place, HomeStay homeStay);

    @Mapping(target = "id", source = "detailPlace.id")
    @Mapping(target = "id_homeStay", source = "detailPlace.homeStay.id")
    @Mapping(target = "placeName", source = "detailPlace.place.placeName")
    @Mapping(target = "image", source = "detailPlace.place.image")
    @Mapping(target = "id_place", source = "detailPlace.place.id")
    public abstract DetailPlaceResponse mapToDto(DetailPlace detailPlace);

}
