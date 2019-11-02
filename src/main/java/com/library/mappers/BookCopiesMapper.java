package com.library.mappers;

import com.library.domain.BookCopies;
import com.library.domain.dto.BookCopiesDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookCopiesMapper {

    public BookCopies mapToBookCopies(BookCopiesDto bookCopiesDto) {
        return new BookCopies(
                bookCopiesDto.getBookCopyId(),
                bookCopiesDto.getTitle(),
                bookCopiesDto.getHiredBooks(),
                bookCopiesDto.getBookStatus());
    }

    public BookCopiesDto mapToBookCopiesDto(BookCopies bookCopies) {
        return new BookCopiesDto(
                bookCopies.getId(),
                bookCopies.getTitle(),
                bookCopies.getHiredBooks(),
                bookCopies.getBookStatus());
    }

    public List<BookCopiesDto> mapToListBookCopiesDto(List<BookCopies> bookCopiesList) {
        return bookCopiesList.stream()
                .map(bookCopies -> new BookCopiesDto(
                        bookCopies.getId(),
                        bookCopies.getTitle(),
                        bookCopies.getHiredBooks(),
                        bookCopies.getBookStatus()))
                .collect(Collectors.toList());
    }
}
