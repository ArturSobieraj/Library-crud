package com.library.controllers;

import com.library.domain.dto.BookTitlesDto;
import com.library.exceptions.BookTitleNotFoundException;
import com.library.mappers.BookTitlesMapper;
import com.library.services.BookTitlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/bookTitles")
public class BookTitlesController {
    @Autowired
    private BookTitlesMapper bookTitlesMapper;
    @Autowired
    private BookTitlesService bookTitlesService;

    @GetMapping(value = "getTitles")
    public List<BookTitlesDto> getTitles() {
        return bookTitlesMapper.mapToBookTitlesDtoList(bookTitlesService.getAllTitles());
    }

    @GetMapping(value = "getTitleById")
    public BookTitlesDto getTitlesById(@RequestParam Long bookTitleId) throws BookTitleNotFoundException {
        return bookTitlesMapper.mapToBookTitlesDto(bookTitlesService.getTitleById(bookTitleId).orElseThrow(BookTitleNotFoundException::new));
    }

    @GetMapping(value = "getTitleByName")
    public List<BookTitlesDto> getTitleByName(@RequestParam String titleName)  {
        return bookTitlesMapper.mapToBookTitlesDtoList(bookTitlesService.getTitleByName(titleName));
    }

    @GetMapping(value = "getTitlesByAuthor")
    public List<BookTitlesDto> getTitlesByAuthor(@RequestParam String author) {
        return bookTitlesMapper.mapToBookTitlesDtoList(bookTitlesService.getTitlesByAuthor(author));
    }

    @GetMapping(value = "getTitlesByYearOfPublication")
    public List<BookTitlesDto> getTitlesByYearOfPublication(@RequestParam String yearOfPublication) {
        return bookTitlesMapper.mapToBookTitlesDtoList((bookTitlesService.getByYearOfPublication(yearOfPublication)));
    }

    @PostMapping(value = "addTitle", consumes = APPLICATION_JSON_VALUE)
    public void addTitle(@RequestBody BookTitlesDto bookTitlesDto) {
        bookTitlesService.addTitle(bookTitlesMapper.mapToBookTitles(bookTitlesDto));
    }

    @PutMapping(value = "updateTitle", consumes = APPLICATION_JSON_VALUE)
    public BookTitlesDto updateTitle(@RequestBody BookTitlesDto bookTitlesDto) {
        return bookTitlesMapper.mapToBookTitlesDto(bookTitlesService.addTitle(bookTitlesMapper.mapToBookTitles(bookTitlesDto)));
    }

    @DeleteMapping(value = "deleteTitle")
    public void deleteTitle(@RequestParam Long bookTitleId) {
        bookTitlesService.deleteTitle(bookTitleId);
    }
}
