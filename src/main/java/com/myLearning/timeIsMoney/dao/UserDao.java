package com.myLearning.timeIsMoney.dao;

import com.myLearning.timeIsMoney.dto.UserDto;
import com.myLearning.timeIsMoney.entity.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User findById(int id);

    boolean save(UserDto userDto);
}
