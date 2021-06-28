package com.booking.homestay.employee.mapper;

import com.booking.homestay.employee.dto.PostRequest;
import com.booking.homestay.employee.dto.PostResponse;
import com.booking.homestay.model.Post;
import com.booking.homestay.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Mapping(target = "id",  ignore = true )
    @Mapping(target = "title", source = "postRequest.title" )
    @Mapping(target = "description", source = "postRequest.description" )
    @Mapping(target = "createDate", expression = "java(java.time.Instant.now())" )
    @Mapping(target = "typePost.id", source = "postRequest.id_typePost" )
    @Mapping(target = "homeStay", source = "user.homeStay" )
    @Mapping(target = "user", source = "user" )
    public abstract Post map(PostRequest postRequest, User user);


    @Mapping(target = "id",  source = "post.id")
    @Mapping(target = "title", source = "post.title" )
    @Mapping(target = "description", source = "post.description" )
    @Mapping(target = "createDate", source = "post.createDate")
    @Mapping(target = "typePostName",  source = "post.typePost.typeName")
    @Mapping(target = "id_typePost",  source = "post.typePost.id")
    @Mapping(target = "id_homeStay", source = "post.homeStay.id" )
    @Mapping(target = "userName", source = "post.user.userName" )
    public abstract PostResponse mapToDto(Post post);


    @Mapping(target = "id",source = "postRequest.id" )
    @Mapping(target = "title", source = "postRequest.title" )
    @Mapping(target = "description", source = "postRequest.description" )
    @Mapping(target = "createDate", source = "post.createDate")
    @Mapping(target = "typePost.id", source = "postRequest.id_typePost" )
    @Mapping(target = "homeStay", source = "post.homeStay" )
    @Mapping(target = "user", source = "post.user" )
    public abstract Post mapEditToDtoById(PostRequest postRequest, Post post);

}
