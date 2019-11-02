package com.library.entities;

import com.library.domain.BookCopies;
import com.library.domain.BookTitles;
import com.library.domain.HiredBooks;
import com.library.domain.User;
import com.library.domain.dao.BookCopiesDao;
import com.library.domain.dao.BookTitlesDao;
import com.library.domain.dao.HiredBooksDao;
import com.library.domain.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class HiredBooksTestSuite {
    @Autowired
    private HiredBooksDao hiredBooksDao;
    @Autowired
    private BookCopiesDao bookCopiesDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookTitlesDao bookTitlesDao;

    @Test
    public void testSaveHiredBook() {
        //Given
        User user1 = new User();
        user1.setUserName("User 1");
        user1.setAccountCreated(LocalDate.now());
        BookCopies book1 = new BookCopies();
        HiredBooks hiredBook = new HiredBooks();
        hiredBook.setDateOfRent(LocalDate.now());
        hiredBook.setDateOfReturn(LocalDate.now().plusDays(5));
        book1.setHiredBooks(hiredBook);
        user1.getHiredBooks().add(hiredBook);
        hiredBook.setUser(user1);
        hiredBook.setBookCopy(book1);

        //When
        userDao.save(user1);
        bookCopiesDao.save(book1);
        hiredBooksDao.save(hiredBook);
        Optional<HiredBooks> getHiredBook = hiredBooksDao.findById(hiredBook.getId());

        //Then
        Assert.assertTrue(getHiredBook.isPresent());

        //CleanUp
        hiredBooksDao.deleteById(hiredBook.getId());
        userDao.deleteById(user1.getId());
        bookCopiesDao.deleteById(book1.getId());
    }

    @Test
    public void testGetAllBooks() {
        //Given
        User user1 = new User();
        user1.setUserName("User");
        user1.setAccountCreated(LocalDate.now());
        BookCopies book1 = new BookCopies();
        BookTitles title1 = new BookTitles();
        title1.setTitle("Title1");
        title1.setAuthor("Author1");
        title1.setYearOfPublication("2000");
        title1.getBookCopies().add(book1);
        book1.setTitle(title1);
        HiredBooks hiredBook = new HiredBooks();
        hiredBook.setDateOfRent(LocalDate.now());
        hiredBook.setDateOfReturn(LocalDate.now().plusDays(5));
        HiredBooks hiredBook2 = new HiredBooks();
        hiredBook2.setDateOfRent(LocalDate.now());
        hiredBook2.setDateOfReturn(LocalDate.now().plusDays(5));
        hiredBook.setUser(user1);
        hiredBook.setBookCopy(book1);
        hiredBook2.setUser(user1);
        hiredBook2.setBookCopy(book1);
        book1.setHiredBooks(hiredBook);
        user1.getHiredBooks().add(hiredBook);
        book1.setHiredBooks(hiredBook2);
        user1.getHiredBooks().add(hiredBook2);


        //When
        bookTitlesDao.save(title1);
        userDao.save(user1);
        bookCopiesDao.save(book1);
        hiredBooksDao.save(hiredBook);
        hiredBooksDao.save(hiredBook2);
        List<HiredBooks> resultList = hiredBooksDao.findAll();

        //Then
        Assert.assertTrue(resultList.size() != 0);

        //CleanUp
        userDao.deleteById(user1.getId());
        bookCopiesDao.deleteById(book1.getId());
        bookTitlesDao.deleteById(title1.getId());
    }
    @Test
    public void testGettingBooks() {
        //Given
        LocalDate now = LocalDate.now();
        User user1 = new User();
        user1.setUserName("User");
        user1.setAccountCreated(LocalDate.now());
        BookCopies book1 = new BookCopies();
        BookTitles title1 = new BookTitles();
        title1.setTitle("Title 1");
        title1.setAuthor("Author 1");
        title1.setYearOfPublication("2000");
        title1.getBookCopies().add(book1);
        book1.setTitle(title1);
        HiredBooks hiredBook = new HiredBooks();
        hiredBook.setDateOfRent(now);
        hiredBook.setDateOfReturn(now.plusDays(5));
        hiredBook.setUser(user1);
        hiredBook.setBookCopy(book1);
        HiredBooks hiredBook2 = new HiredBooks();
        hiredBook2.setDateOfRent(now);
        hiredBook2.setDateOfReturn(now.plusDays(5));
        hiredBook2.setUser(user1);
        hiredBook2.setBookCopy(book1);
        HiredBooks hiredBook3 = new HiredBooks();
        hiredBook3.setDateOfRent(LocalDate.now().minusDays(1));
        hiredBook3.setDateOfReturn(LocalDate.now().plusDays(7));
        hiredBook3.setUser(user1);
        hiredBook3.setBookCopy(book1);
        book1.setHiredBooks(hiredBook);
        user1.getHiredBooks().add(hiredBook);
        book1.setHiredBooks(hiredBook2);
        user1.getHiredBooks().add(hiredBook2);

        //When
        bookTitlesDao.save(title1);
        userDao.save(user1);
        bookCopiesDao.save(book1);
        hiredBooksDao.save(hiredBook);
        hiredBooksDao.save(hiredBook2);
        hiredBooksDao.save(hiredBook3);
        List<HiredBooks> hiredBooksListGetByUser = hiredBooksDao.findByUser(user1);
        List<HiredBooks> hiredBooksListGetByBookCopy = hiredBooksDao.findByBookCopy(book1);
        List<HiredBooks> hiredBooksListGetByRentDate = hiredBooksDao.findByDateOfRent(now);
        List<HiredBooks> hiredBooksListGetByReturnDate = hiredBooksDao.findByDateOfReturn(now.plusDays(5));

        //Then
        Assert.assertTrue(hiredBooksListGetByUser.size() != 0);
        Assert.assertTrue(hiredBooksListGetByBookCopy.size() != 0);
        Assert.assertTrue(hiredBooksListGetByRentDate.size() != 0);
        Assert.assertTrue(hiredBooksListGetByReturnDate.size() != 0);

        //CleanUp
        userDao.deleteById(user1.getId());
        bookCopiesDao.deleteById(book1.getId());
        bookTitlesDao.deleteById(title1.getId());
    }
}
