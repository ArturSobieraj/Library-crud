package com.library.services;

import com.library.domain.BookTitles;
import com.library.domain.dao.BookTitlesDao;
import com.library.exceptions.BookTitleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookTitlesService {
    @Autowired
    private BookTitlesDao bookTitlesDao;

    public List<BookTitles> getAllTitles() {
        return bookTitlesDao.findAll();
    }

    public Optional<BookTitles> getTitleById(Long titleId) throws BookTitleNotFoundException{
        return Optional.ofNullable(bookTitlesDao.findById(titleId).orElseThrow(BookTitleNotFoundException::new));
    }

    public List<BookTitles> getTitleByName(String titleName) {
        return bookTitlesDao.findByTitle(titleName);
    }

    public List<BookTitles> getTitlesByAuthor(String author) {
        return bookTitlesDao.findByAuthor(author);
    }

    public List<BookTitles> getByYearOfPublication(String yearOfPublication) {
        return bookTitlesDao.findByYearOfPublication(yearOfPublication);
    }

    public BookTitles addTitle(BookTitles bookTitles) {
        return bookTitlesDao.save(bookTitles);
    }

    public void deleteTitle(Long titleId) {
        bookTitlesDao.deleteById(titleId);
    }
}
