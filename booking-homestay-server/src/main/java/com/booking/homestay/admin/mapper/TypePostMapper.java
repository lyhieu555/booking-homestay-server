package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.TypePostRequest;
import com.booking.homestay.admin.dto.TypePostResponse;
import com.booking.homestay.employee.dto.PostResponse;
import com.booking.homestay.employee.service.PostService;
import com.booking.homestay.model.Post;
import com.booking.homestay.model.TypePost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class TypePostMapper {

    @Autowired
    private PostService postService;

    @Mapping(target = "id",  ignore = true )
    @Mapping(target = "typeName", source = "typePostRequest.typeName" )
    public abstract TypePost map(TypePostRequest typePostRequest);


    @Mapping(target = "id",  source = "typePost.id")
    @Mapping(target = "typeName", source = "typePost.typeName" )
    @Mapping(target = "countPost", expression = "java(getCountPost(typePost))" )
    public abstract TypePostResponse mapToDto(TypePost typePost);

    @Mapping(target = "id",  source = "typePost.id")
    @Mapping(target = "typeName", source = "typePost.typeName" )
    @Mapping(target = "countPost", expression = "java(getCountPost2(typePost))" )
    public abstract TypePostResponse mapToDto2(TypePost typePost);

    Integer getCountPost(TypePost typePost){
        return postService.getAllPostByType(typePost.getId()).toArray().length;
    }

    Integer getCountPost2(TypePost typePost){
        return postService.countPost(typePost.getId()).toArray().length;
    }

    @Mapping(target = "id",  source = "typePostRequest.id" )
    @Mapping(target = "typeName", source = "typePostRequest.typeName" )
    public abstract TypePost mapEditToDtoById(TypePostRequest typePostRequest, TypePost typePost);

}
