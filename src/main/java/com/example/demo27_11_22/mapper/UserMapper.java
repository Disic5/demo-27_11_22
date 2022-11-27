package com.example.demo27_11_22.mapper;

import com.example.demo27_11_22.dto.UserDto;
import com.example.demo27_11_22.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto toDto(User user);
    User toEntity(UserDto dto);

    List<UserDto> toDto(List<User>users);
    void updateUser(UserDto dto,@MappingTarget User user);

}
