package com.library.entities;

import com.library.domain.BookTitles;
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
public class BookTitlesTestSuite {
    @Autowired
    private BookTitlesDao bookTitlesDao;

    @Test
    public void testSaveBookTitle() {
        //Given
        BookTitles title1 = new BookTitles();
        title1.setTitle("Title 1");
        title1.setAuthor("Author 1");
        title1.setYearOfPublication("2000");

        //When
        bookTitlesDao.save(title1);
        Optional<BookTitles> getTitle = bookTitlesDao.findById(title1.getId());

        //Then
        Assert.assertTrue(getTitle.isPresent());

        //CleanUp
        bookTitlesDao.deleteById(title1.getId());
    }

    @Test
    public void testGettingByTitles() {
        //Given
        BookTitles title1 = new BookTitles();
        BookTitles title2 = new BookTitles();
        BookTitles title3 = new BookTitles();
        title1.setTitle("Title 1");
        title1.setAuthor("Author 1");
        title1.setYearOfPublication("2000");
        title2.setTitle("Title 2");
        title2.setAuthor("Author 2");
        title2.setYearOfPublication("2000");
        title3.setTitle("Title 3");
        title3.setAuthor("Author 3");
        title3.setYearOfPublication("2010");

        //When
        bookTitlesDao.save(title1);
        bookTitlesDao.save(title2);
        bookTitlesDao.save(title3);
        List<BookTitles> getAllTitles = bookTitlesDao.findAll();
        List<BookTitles> getTitleByName = bookTitlesDao.findByTitle("Title 1");
        List<BookTitles> getTitleByAuthor = bookTitlesDao.findByAuthor("Author 2");
        List<BookTitles> getTitleByYearOfPublication = bookTitlesDao.findByYearOfPublication("2000");

        //Then
        Assert.assertTrue(getAllTitles.size() != 0);
        Assert.assertTrue(getTitleByName.size() != 0);
        Assert.assertTrue(getTitleByAuthor.size() != 0);
        Assert.assertTrue(getTitleByYearOfPublication.size() != 0);

        //CleanUp
        bookTitlesDao.deleteById(title1.getId());
        bookTitlesDao.deleteById(title2.getId());
        bookTitlesDao.deleteById(title3.getId());
    }
}
