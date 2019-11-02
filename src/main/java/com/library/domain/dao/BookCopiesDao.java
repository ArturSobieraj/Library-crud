package com.library.domain.dao;

import com.library.domain.BookCopies;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookCopiesDao extends CrudRepository<BookCopies, Long> {

    @Override
    List<BookCopies> findAll();

    Optional<BookCopies> findById(Long id);

    List<BookCopies> findByBookStatus(String status);

    @Override
    BookCopies save(BookCopies bookCopies);

    @Override
    void deleteById(Long id);
}
