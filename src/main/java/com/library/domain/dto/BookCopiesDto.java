package com.library.domain.dto;

import com.library.domain.BookTitles;
import com.library.domain.HiredBooks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookCopiesDto {

    private Long bookCopyId;
    private BookTitles title;
    private HiredBooks hiredBooks;
    private String bookStatus;
}
