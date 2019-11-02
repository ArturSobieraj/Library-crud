package com.library.services;

import com.library.domain.BookCopies;
import com.library.domain.HiredBooks;
import com.library.domain.User;
import com.library.domain.dao.HiredBooksDao;
import com.library.exceptions.BookHiredNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HiredBooksService {
    @Autowired
    private HiredBooksDao hiredBooksDao;

    public List<HiredBooks> getAllHires() {
        return hiredBooksDao.findAll();
    }

    public Optional<HiredBooks> getHireById(Long id) throws BookHiredNotFoundException {
        return Optional.ofNullable(hiredBooksDao.findById(id)).orElseThrow(BookHiredNotFoundException::new);
    }

    public List<HiredBooks> getHiresByUser(User user) {
        return hiredBooksDao.findByUser(user);
    }

    public List<HiredBooks> getHiresByBookCopies(BookCopies bookCopies) {
        return hiredBooksDao.findByBookCopy(bookCopies);
    }

    public List<HiredBooks> getHiresByDateOfRent(LocalDate dateOfRent) {
        return hiredBooksDao.findByDateOfRent(dateOfRent);
    }

    public List<HiredBooks> getHiresByDateOfReturn(LocalDate dateOfReturn) {
        return hiredBooksDao.findByDateOfReturn(dateOfReturn);
    }

    public HiredBooks newHire(HiredBooks hiredBooks) {
        return hiredBooksDao.save(hiredBooks);
    }

    public void deleteHire(Long id) {
        hiredBooksDao.deleteById(id);
    }
}
