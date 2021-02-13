package com.mylearning.timeismoney.service;

import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.dao.UserDao;
import com.mylearning.timeismoney.dto.UserDto;
import com.mylearning.timeismoney.entity.User;

import java.util.List;

public class UserService {

    private final UserDao userDao;

    public UserService(DaoFactory daoFactory) {
        this.userDao = daoFactory.createUserDao();
    }


    public User findByLogin(String login) {
        return userDao.findByLogin(login)
                .orElseThrow(()-> new RuntimeException());
    }

    public boolean create(UserDto userDto) {
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());

        return userDao.create(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
