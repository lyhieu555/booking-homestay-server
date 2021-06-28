package com.booking.homestay.employee.mapper;

import com.booking.homestay.employee.dto.DetailViewResponse;
import com.booking.homestay.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class DetailViewMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "view", source = "view")
    @Mapping(target = "house", source = "house")
    public abstract DetailView mapToSave(View view, House house);

    @Mapping(target = "id", source = "detailView.id")
    @Mapping(target = "id_house", source = "detailView.house.id")
    @Mapping(target = "viewName", source = "detailView.view.viewName")
    @Mapping(target = "image", source = "detailView.view.image")
    @Mapping(target = "id_view", source = "detailView.view.id")
    public abstract DetailViewResponse mapToDto(DetailView detailView);

}
