package com.example.demo27_11_22.mapper;

import com.example.demo27_11_22.dto.UserDto;
import com.example.demo27_11_22.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void toDto() {
        User user = new User();
        user.setId(1L);
        user.setName("Ivan");
        UserDto dto = UserMapper.INSTANCE.toDto(user);
        assertEquals(dto.getId(),user.getId());
        assertEquals(dto.getName(),user.getName());
    }

    @Test
    void toEntity() {
        UserDto dto = new UserDto();
        dto.setId(2L);
        dto.setName("Ilon");

        User user = UserMapper.INSTANCE.toEntity(dto);
        assertEquals(user.getId(),dto.getId());
        assertEquals(user.getName(),dto.getName());
    }
}