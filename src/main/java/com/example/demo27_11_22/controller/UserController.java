package com.example.demo27_11_22.controller;

import com.example.demo27_11_22.dto.UserDto;
import com.example.demo27_11_22.entity.User;
import com.example.demo27_11_22.mapper.UserMapper;
import com.example.demo27_11_22.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @GetMapping
    ResponseEntity<List<UserDto>> showAllUsers(){
        return new ResponseEntity<>(mapper.toDto(service.findAllUsers()), HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    ResponseEntity<UserDto> findUserById(@PathVariable Long id){
        Optional<User> user = service.finBbyId(id);
        if (user.isPresent()){
            return new ResponseEntity<>(mapper.toDto(user.get()),HttpStatus.OK);
        }else {
            throw new NoSuchFieldException("User with this id= " + id + " not found");
        }
    }
    @PostMapping()
    ResponseEntity<UserDto>addNewUser(@RequestBody UserDto dto){
        User user = mapper.toEntity(dto);
        User userSave = service.saveUser(user);
        return new ResponseEntity<>(mapper.toDto(userSave),HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    ResponseEntity<UserDto> updateUserById(@PathVariable long id,@RequestBody UserDto userDto){
        if (service.finBbyId(id).isPresent()){
            userDto.setId(id);
            User user = service.finBbyId(id).get();
            mapper.updateUser(userDto,user);
            service.saveUser(user);
            return new ResponseEntity<>(mapper.toDto(service.finBbyId(id).get()),HttpStatus.ACCEPTED);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUserById(@PathVariable long id){
        if (service.finBbyId(id).isPresent()){
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}



