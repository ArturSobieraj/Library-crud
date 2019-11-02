package com.library.domain.dto;

import com.library.domain.BookCopies;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookTitlesDto {

    private Long titleId;
    private String title;
    private String author;
    private String yearOfPublication;
    private List<BookCopies> bookCopies;
    private int numberOfCopies;
    private int booksReadyToRent;
}
