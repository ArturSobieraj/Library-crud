package com.library.entities;

import com.library.domain.HiredBooks;
import com.library.domain.User;
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
public class UserTestSuite {
    @Autowired
    private UserDao userDao;
    @Autowired
    private HiredBooksDao hiredBooksDao;

    @Test
    public void testSaveUser() {
        //Given
        User user1 = new User();
        user1.setAccountCreated(LocalDate.now());
        user1.setUserName("Test 1");

        //When
        userDao.save(user1);
        Optional<User> getUser = userDao.findById(user1.getId());

        //Then
        Assert.assertTrue(getUser.isPresent());

        //CleanUp
        userDao.deleteById(user1.getId());
    }

    @Test
    public void testFindUserByName() {
        //Given
        User user1 = new User();
        user1.setAccountCreated(LocalDate.now());
        user1.setUserName("Test 2");

        //When
        userDao.save(user1);
        Optional<User> getUser = userDao.findByUserName(user1.getUserName());

        //Then
        Assert.assertTrue(getUser.isPresent());

        //CleanUp
        userDao.deleteById(user1.getId());
    }

    @Test
    public void testSaveUserWithHiredBooks() {
        //Given
        HiredBooks book1 = new HiredBooks();
        HiredBooks book2 = new HiredBooks();
        User user1 = new User();
        user1.setUserName("Name 1");
        book1.setDateOfRent(LocalDate.now());
        book1.setDateOfReturn(LocalDate.now().plusDays(7));
        book2.setDateOfRent(LocalDate.now());
        book2.setDateOfReturn(LocalDate.now().plusDays(15));
        user1.setAccountCreated(LocalDate.now());
        book1.setUser(user1);
        book2.setUser(user1);
        user1.getHiredBooks().add(book1);
        user1.getHiredBooks().add(book2);

        //When
        userDao.save(user1);
        hiredBooksDao.save(book1);
        hiredBooksDao.save(book2);
        Optional<User> getUser = userDao.findById(user1.getId());

        //Then
        Assert.assertTrue(getUser.isPresent());

        //CleanUp
        hiredBooksDao.deleteById(book1.getId());
        hiredBooksDao.deleteById(book2.getId());
        userDao.deleteById(user1.getId());
    }

    @Test
    public void testGetAllUsers() {
        //Given
        User user1 = new User();
        User user2 = new User();
        user1.setUserName("Name 2");
        user2.setUserName("Name 3");
        user1.setAccountCreated(LocalDate.now());
        user2.setAccountCreated(LocalDate.now());

        //When
        userDao.save(user1);
        userDao.save(user2);
        List<User> resultList = userDao.findAll();

        //Then
        Assert.assertTrue(resultList.size() != 0);

        //CleanUp
        userDao.deleteById(user1.getId());
        userDao.deleteById(user2.getId());

    }
}
