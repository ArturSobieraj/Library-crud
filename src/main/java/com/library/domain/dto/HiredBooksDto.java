package com.library.domain.dto;

import com.library.domain.BookCopies;
import com.library.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HiredBooksDto {

    private Long hireId;
    private BookCopies bookCopies;
    private User user;
    private LocalDate dateOfRent;
    private LocalDate dateOfReturn;
}
