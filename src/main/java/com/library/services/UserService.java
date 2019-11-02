package com.library.services;

import com.library.domain.User;
import com.library.domain.dao.UserDao;
import com.library.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public Optional<User> getUser(Long id) throws UserNotFoundException {
        return Optional.ofNullable(userDao.findById(id)).orElseThrow(UserNotFoundException::new);
    }

    public Optional<User> getUserByName(String userName) throws UserNotFoundException{
        return Optional.ofNullable(userDao.findByUserName(userName)).orElseThrow(UserNotFoundException::new);
    }

    public User addUser(User user) {
        return userDao.save(user);
    }

    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }
}
