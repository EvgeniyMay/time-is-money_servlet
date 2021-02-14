package com.mylearning.timeismoney.service;

import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.dao.UserDao;
import com.mylearning.timeismoney.dto.UserDto;
import com.mylearning.timeismoney.entity.User;

import java.util.List;

public class UserService {

    private final DaoFactory daoFactory;

    public UserService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }


    public List<User> findAll() {
        try(UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll();
        }
    }

    public User findByLoginProxy(String login) {
        try(UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByLoginProxy(login)
                    .orElseThrow(() -> new RuntimeException());
        }
    }

    public User findById(int id) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findById(id)
                    .orElseThrow(() -> new RuntimeException());
        }
    }

    public boolean create(UserDto userDto) {
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());

        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.create(user);
        }
    }
}
