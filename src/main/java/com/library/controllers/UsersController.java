package com.library.controllers;

import com.library.domain.dto.UserDto;
import com.library.exceptions.UserNotFoundException;
import com.library.mappers.UserMapper;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/users")
public class UsersController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "getAllUsers")
    public List<UserDto> getAllUsers() {
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    @GetMapping(value = "getUserById")
    public UserDto getUserById(@RequestParam Long id) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUser(id).orElseThrow(UserNotFoundException::new));
    }

    @GetMapping(value = "getUserByName")
    public UserDto getUserByName(@RequestParam String userName) throws UserNotFoundException{
        return userMapper.mapToUserDto(userService.getUserByName(userName).orElseThrow(UserNotFoundException::new));
    }

    @PostMapping(value = "addUser", consumes = APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserDto userDto) {
        userService.addUser(userMapper.mapToUser(userDto));
    }

    @PutMapping(value = "editUser", consumes = APPLICATION_JSON_VALUE)
    public UserDto editUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userService.addUser(userMapper.mapToUser(userDto)));
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }
}
