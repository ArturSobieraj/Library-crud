package com.library.domain.dao;

import com.library.domain.BookCopies;
import com.library.domain.HiredBooks;
import com.library.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface HiredBooksDao extends CrudRepository<HiredBooks, Long> {

    @Override
    List<HiredBooks> findAll();

    @Override
    Optional<HiredBooks> findById(Long hireId);

    @Override
    HiredBooks save(HiredBooks hiredBooks);

    @Override
    void deleteById(Long id);

    List<HiredBooks> findByDateOfRent(LocalDate dateOfRent);

    List<HiredBooks> findByDateOfReturn(LocalDate dateOfReturn);

    List<HiredBooks> findByUser(User user);

    List<HiredBooks> findByBookCopy(BookCopies bookCopies);
}
