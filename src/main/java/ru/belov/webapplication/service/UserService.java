package ru.belov.webapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.belov.webapplication.dao.UserDao;
import ru.belov.webapplication.model.User;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findNotConfirmMail() {
        return userDao.findNotConfirmMail();
    }

    public List<User> findNotLogin() {
        return userDao.findNotLogin();
    }

    public List<User> findUsersActiveToken() {
        return userDao.findUsersActiveToken();
    }

    public List<User> findTopFailLogin() {
        return userDao.findTopFailLogin();
    }

}
