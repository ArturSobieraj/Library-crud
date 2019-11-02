package com.library.services;

import com.library.domain.BookCopies;
import com.library.domain.dao.BookCopiesDao;
import com.library.exceptions.BookCopyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCopiesService {
    @Autowired
    private BookCopiesDao bookCopiesDao;

    public List<BookCopies> getAllBookCopies() {
        return bookCopiesDao.findAll();
    }

    public Optional<BookCopies> getBookCopy(Long bookCopyId) throws BookCopyNotFoundException {
        return Optional.ofNullable(bookCopiesDao.findById(bookCopyId)).orElseThrow(BookCopyNotFoundException::new);
    }

    public List<BookCopies> getBookCopiesByStatus(String bookStatus) {
        return bookCopiesDao.findByBookStatus(bookStatus);
    }

    public BookCopies saveBookCopy(BookCopies bookCopies) {
        return bookCopiesDao.save(bookCopies);
    }

    public void deleteBookCopy(Long id) {
        bookCopiesDao.deleteById(id);
    }
}
