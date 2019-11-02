package com.library.domain.dto;

import com.library.domain.HiredBooks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {

    private Long userId;
    private String userName;
    private LocalDate accountCreated;
    private List<HiredBooks> hiredBooks;
}
