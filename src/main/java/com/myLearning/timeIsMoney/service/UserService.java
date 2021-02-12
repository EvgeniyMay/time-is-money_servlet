package com.myLearning.timeIsMoney.service;

import com.myLearning.timeIsMoney.dao.DaoFactory;
import com.myLearning.timeIsMoney.dao.UserDao;
import com.myLearning.timeIsMoney.dto.UserDto;
import com.myLearning.timeIsMoney.entity.User;

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