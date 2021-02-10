package com.myLearning.timeIsMoney.dao;

import com.myLearning.timeIsMoney.dto.UserDto;
import com.myLearning.timeIsMoney.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll();

    Optional<User> findById(int id);

    boolean save(UserDto userDto);

    Optional<User> findByLogin(String login);
}
