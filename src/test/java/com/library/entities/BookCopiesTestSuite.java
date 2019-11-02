package com.library.entities;

import com.library.domain.BookCopies;
import com.library.domain.BookTitles;
import com.library.domain.dao.BookCopiesDao;
import com.library.domain.dao.BookTitlesDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCopiesTestSuite {
    @Autowired
    private BookCopiesDao bookCopiesDao;
    @Autowired
    private BookTitlesDao bookTitlesDao;

    @Test
    public void testGetBookCopyById() {
        //Given
        BookCopies book1 = new BookCopies();
        BookTitles title1 = new BookTitles();
        title1.setTitle("Title 1");
        title1.setAuthor("Author 1");
        title1.setYearOfPublication("2000");
        book1.setTitle(title1);
        title1.getBookCopies().add(book1);

        //When
        bookTitlesDao.save(title1);
        bookCopiesDao.save(book1);
        Optional<BookCopies> getCopy = bookCopiesDao.findById(book1.getId());

        //Then
        Assert.assertTrue(getCopy.isPresent());

        //CleanUp
        bookCopiesDao.deleteById(book1.getId());
        bookTitlesDao.deleteById(title1.getId());
    }

    @Test
    public void testGetBookByTitle() {
        //Given
        BookCopies book1 = new BookCopies();
        BookCopies book2 = new BookCopies();
        BookTitles title1 = new BookTitles();
        title1.setTitle("Title1");
        title1.setAuthor("Author1");
        title1.setYearOfPublication("2000");
        book1.setTitle(title1);
        book1.setBookStatus("destroyed");
        book2.setTitle(title1);
        book2.setBookStatus("Rented");
        title1.getBookCopies().add(book1);
        title1.getBookCopies().add(book2);

        //When
        bookTitlesDao.save(title1);
        bookCopiesDao.save(book1);
        bookCopiesDao.save(book2);
        List<BookCopies> booksStatusList = bookCopiesDao.findByBookStatus("destroyed");

        //Then
        Assert.assertTrue(booksStatusList.size() != 0);

        //CleanUp
        bookCopiesDao.deleteById(book1.getId());
        bookCopiesDao.deleteById(book2.getId());
        bookTitlesDao.deleteById(title1.getId());

    }
}
