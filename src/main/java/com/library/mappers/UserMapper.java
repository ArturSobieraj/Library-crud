package com.library.mappers;

import com.library.domain.User;
import com.library.domain.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(UserDto userDto) {
        return new User(
             userDto.getUserId(),
             userDto.getUserName(),
             userDto.getAccountCreated(),
             userDto.getHiredBooks());
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getAccountCreated(),
                user.getHiredBooks());
    }

    public List<UserDto> mapToUserDtoList(List<User> userList) {
        return userList.stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUserName(),
                        user.getAccountCreated(),
                        user.getHiredBooks()))
                .collect(Collectors.toList());
    }
}
