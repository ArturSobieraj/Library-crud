package com.library.domain.dao;

import com.library.domain.BookTitles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookTitlesDao extends CrudRepository<BookTitles, Long> {

    @Override
    List<BookTitles> findAll();

    List<BookTitles> findByTitle(String title);

    @Override
    Optional<BookTitles> findById(Long titleId);

    List<BookTitles> findByAuthor(String author);

    List<BookTitles> findByYearOfPublication(String yearOfPublication);

    @Override
    void deleteById(Long id);

    @Override
    BookTitles save(BookTitles bookTitles);
}
