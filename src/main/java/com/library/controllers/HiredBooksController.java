package com.library.controllers;

import com.library.domain.BookCopies;
import com.library.domain.BookTitles;
import com.library.domain.HiredBooks;
import com.library.domain.User;
import com.library.domain.dto.HiredBooksDto;
import com.library.exceptions.BookCopyNotFoundException;
import com.library.exceptions.BookHiredNotFoundException;
import com.library.exceptions.NoAvailableBookException;
import com.library.mappers.HiredBooksMapper;
import com.library.services.BookCopiesService;
import com.library.services.BookTitlesService;
import com.library.services.HiredBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/hirebook")
public class HiredBooksController {
    @Autowired
    private HiredBooksMapper hiredBooksMapper;
    @Autowired
    private HiredBooksService hiredBooksService;
    @Autowired
    private BookCopiesService bookCopiesService;
    @Autowired
    private BookTitlesService bookTitlesService;

    @GetMapping(value = "getAllHires")
    public List<HiredBooksDto> getAllHires() {
        return hiredBooksMapper.mapToHiredBooksDtoList(hiredBooksService.getAllHires());
    }

    @GetMapping(value = "getHireById")
    public HiredBooksDto getHireById(@RequestParam Long id) throws BookHiredNotFoundException {
        return hiredBooksMapper.mapToHiredBooksDto(hiredBooksService.getHireById(id).orElseThrow(BookHiredNotFoundException::new));
    }

    @GetMapping(value = "getHiresByUser")
    public List<HiredBooksDto> getHiresByUser(@RequestBody User user) {
        return hiredBooksMapper.mapToHiredBooksDtoList(hiredBooksService.getHiresByUser(user));
    }

    @GetMapping(value = "getHireByBookCopy")
    public List<HiredBooksDto> getHireByBookCopy(@RequestBody BookCopies bookCopies) {
        return hiredBooksMapper.mapToHiredBooksDtoList(hiredBooksService.getHiresByBookCopies(bookCopies));
    }

    @GetMapping(value = "getHiresByDateOfRent")
    public List<HiredBooksDto> getHiresByDateOfRent(@RequestBody LocalDate dateOfRent) {
        return hiredBooksMapper.mapToHiredBooksDtoList(hiredBooksService.getHiresByDateOfRent(dateOfRent));
    }

    @GetMapping(value = "getHiresByDateOfReturn")
    public List<HiredBooksDto> getHiresByDateOfReturn(@RequestBody LocalDate dateOfReturn) {
        return hiredBooksMapper.mapToHiredBooksDtoList(hiredBooksService.getHiresByDateOfReturn(dateOfReturn));
    }

    @PostMapping(value = "newHire", consumes = APPLICATION_JSON_VALUE)
    public void newHire(@RequestBody HiredBooksDto hiredBooksDto, @RequestParam Long bookCopyId) throws NoAvailableBookException {
        HiredBooks bookToHire = hiredBooksMapper.mapToHiredBooks(hiredBooksDto);
        BookCopies bookPosition = new BookCopies();
        try {
            bookPosition = bookCopiesService.getBookCopy(bookCopyId).orElseThrow(BookCopyNotFoundException::new);
        } catch (BookCopyNotFoundException e) {
            e.getMessage();
        }
        BookTitles bookTitle = bookPosition.getTitle();
        if (bookPosition.getBookStatus().equals("Available")) {
            bookPosition.setBookStatus("Rented");
            bookTitle.setBooksReadyToRent(bookTitle.getBooksReadyToRent()-1);
            bookPosition.setHiredBooks(bookToHire);
            bookToHire.setBookCopy(bookPosition);
        } else {
            throw new NoAvailableBookException("There are no available books");
        }
        hiredBooksService.newHire(bookToHire);
        bookCopiesService.saveBookCopy(bookPosition);
        bookTitlesService.addTitle(bookTitle);
    }

    @PutMapping(value = "updateHire", consumes = APPLICATION_JSON_VALUE)
    public HiredBooksDto updateHire(@RequestBody HiredBooksDto hiredBooksDto) {
        return hiredBooksMapper.mapToHiredBooksDto(hiredBooksService.newHire(hiredBooksMapper.mapToHiredBooks(hiredBooksDto)));
    }

    @DeleteMapping(value = "returnBook")
    public void returnBook(@RequestParam Long id) {
        HiredBooks returningHire = new HiredBooks();
        try {
            returningHire = hiredBooksService.getHireById(id).orElseThrow(BookHiredNotFoundException::new);
        } catch (BookHiredNotFoundException e) {
            e.getMessage();
        }
        BookCopies bookCopyToReturn = returningHire.getBookCopy();
        BookTitles titleToReturn = bookCopyToReturn.getTitle();
        bookCopyToReturn.setHiredBooks(null);
        titleToReturn.setBooksReadyToRent(titleToReturn.getBooksReadyToRent()+1);
        bookCopyToReturn.setBookStatus("Available");
        bookCopiesService.saveBookCopy(bookCopyToReturn);
        bookTitlesService.addTitle(titleToReturn);
        hiredBooksService.deleteHire(id);
    }
}
