package com.booking.homestay.employee.mapper;

import com.booking.homestay.employee.dto.ViewRequest;
import com.booking.homestay.employee.dto.ViewResponse;
import com.booking.homestay.model.User;
import com.booking.homestay.model.View;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ViewMapper {

    @Mapping(target = "id",  ignore = true )
    @Mapping(target = "viewName", source = "viewRequest.viewName" )
    @Mapping(target = "image", source = "viewRequest.image" )
    public abstract View map(ViewRequest viewRequest);


    @Mapping(target = "id", source = "view.id" )
    @Mapping(target = "viewName", source = "view.viewName" )
    public abstract ViewResponse mapToDto(View view);


    @Mapping(target = "id", source = "viewRequest.id" )
    @Mapping(target = "viewName", source = "viewRequest.viewName" )
    @Mapping(target = "image", source = "viewRequest.image" )
    public abstract View mapEditToDtoById(ViewRequest viewRequest);

}
