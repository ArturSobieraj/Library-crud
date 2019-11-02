package com.library.controllers;

import com.library.domain.BookCopies;
import com.library.domain.BookTitles;
import com.library.domain.dto.BookCopiesDto;
import com.library.exceptions.BookCopyNotFoundException;
import com.library.exceptions.BookTitleNotFoundException;
import com.library.mappers.BookCopiesMapper;
import com.library.services.BookCopiesService;
import com.library.services.BookTitlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/bookCopies")
public class BookCopiesController {
    @Autowired
    private BookCopiesMapper bookCopiesMapper;
    @Autowired
    private BookCopiesService bookCopiesService;
    @Autowired
    private BookTitlesService bookTitlesService;

    @GetMapping(value = "getBookCopies")
    public List<BookCopiesDto> getBookCopies() {
        return bookCopiesMapper.mapToListBookCopiesDto(bookCopiesService.getAllBookCopies());
    }

    @GetMapping(value = "getBookCopy")
    public BookCopiesDto getBookCopy(@RequestParam Long bookCopyId) throws BookCopyNotFoundException {
        return bookCopiesMapper.mapToBookCopiesDto(bookCopiesService.getBookCopy(bookCopyId).orElseThrow(BookCopyNotFoundException::new));
    }

    @GetMapping(value = "getBookCopiesByStatus")
    public List<BookCopiesDto> getBookCopiesByStatus(@RequestParam String bookStatus) {
        return bookCopiesMapper.mapToListBookCopiesDto(bookCopiesService.getBookCopiesByStatus(bookStatus));
    }

    @PostMapping(value = "addBookCopy", consumes = APPLICATION_JSON_VALUE)
    public void addBookCopy(@RequestBody BookCopiesDto bookCopiesDto,@RequestParam Long bookTitleId) {
        BookCopies bookCopy = bookCopiesMapper.mapToBookCopies(bookCopiesDto);
        BookTitles bookTitle = new BookTitles();
        try {
            bookTitle = bookTitlesService.getTitleById(bookTitleId).orElseThrow(BookTitleNotFoundException::new);
        } catch (BookTitleNotFoundException e) {
            e.getMessage();
        }
        bookTitle.getBookCopies().add(bookCopy);
        bookTitle.setNumberOfCopies(bookTitle.getNumberOfCopies()+1);
        bookTitle.setBooksReadyToRent(bookTitle.getBooksReadyToRent()+1);
        bookCopy.setTitle(bookTitle);
        bookTitlesService.addTitle(bookTitle);
        bookCopiesService.saveBookCopy(bookCopy);
    }

    @PutMapping(value = "updateBookCopy", consumes = APPLICATION_JSON_VALUE)
    public BookCopiesDto updateBookCopy(@RequestBody BookCopiesDto bookCopiesDto) {
        return bookCopiesMapper.mapToBookCopiesDto(bookCopiesService.saveBookCopy(bookCopiesMapper.mapToBookCopies(bookCopiesDto)));
    }

    @DeleteMapping(value = "deleteBookCopy")
    public void deleteBookCopy(@RequestParam Long bookCopyId) {
        bookCopiesService.deleteBookCopy(bookCopyId);
    }

    @PutMapping(value = "changeBookStatus")
    public BookCopiesDto changeBookCopyStatus(@RequestParam Long bookCopyId,@RequestParam String bookStatus) {
        BookCopies editedBook = new BookCopies();
        try {
            editedBook = bookCopiesService.getBookCopy(bookCopyId).orElseThrow(BookCopyNotFoundException::new);
        } catch (BookCopyNotFoundException e) {
            e.getMessage();
        }
        editedBook.setBookStatus(bookStatus);
        return bookCopiesMapper.mapToBookCopiesDto(bookCopiesService.saveBookCopy(editedBook));
    }
}
