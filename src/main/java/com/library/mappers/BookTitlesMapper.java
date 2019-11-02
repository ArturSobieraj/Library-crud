package com.library.mappers;

import com.library.domain.BookTitles;
import com.library.domain.dto.BookTitlesDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookTitlesMapper {

    public BookTitles mapToBookTitles(BookTitlesDto bookTitlesDto) {
        return new BookTitles(
                bookTitlesDto.getTitleId(),
                bookTitlesDto.getTitle(),
                bookTitlesDto.getAuthor(),
                bookTitlesDto.getYearOfPublication(),
                bookTitlesDto.getBookCopies(),
                bookTitlesDto.getNumberOfCopies(),
                bookTitlesDto.getBooksReadyToRent());
    }

    public BookTitlesDto mapToBookTitlesDto(BookTitles bookTitles) {
        return new BookTitlesDto(
                bookTitles.getId(),
                bookTitles.getTitle(),
                bookTitles.getAuthor(),
                bookTitles.getYearOfPublication(),
                bookTitles.getBookCopies(),
                bookTitles.getNumberOfCopies(),
                bookTitles.getBooksReadyToRent());
    }

    public List<BookTitlesDto> mapToBookTitlesDtoList(List<BookTitles> bookTitlesList) {
        return bookTitlesList.stream()
                .map(bookTitles -> new BookTitlesDto(
                        bookTitles.getId(),
                        bookTitles.getTitle(),
                        bookTitles.getAuthor(),
                        bookTitles.getYearOfPublication(),
                        bookTitles.getBookCopies(),
                        bookTitles.getNumberOfCopies(),
                        bookTitles.getBooksReadyToRent()))
                .collect(Collectors.toList());
    }
}
