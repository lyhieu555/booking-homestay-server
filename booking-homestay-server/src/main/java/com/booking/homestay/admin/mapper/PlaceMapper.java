package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.PlaceRequest;
import com.booking.homestay.admin.dto.PlaceResponse;
import com.booking.homestay.model.Place;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PlaceMapper {


    @Mapping(target = "id",  ignore = true )
    @Mapping(target = "placeName", source = "placeRequest.placeName" )
    @Mapping(target = "image", source = "placeRequest.image" )
    public abstract Place map(PlaceRequest placeRequest);


    @Mapping(target = "id",  source = "place.id" )
    @Mapping(target = "placeName", source = "place.placeName" )
    @Mapping(target = "image", source = "place.image" )
    public abstract PlaceResponse mapToDto(Place place);


    @Mapping(target = "id",  source = "placeRequest.id"  )
    @Mapping(target = "placeName", source = "placeRequest.placeName" )
    @Mapping(target = "image", source = "placeRequest.image" )
    public abstract Place mapEditToDtoById(PlaceRequest placeRequest, Place place);


}
