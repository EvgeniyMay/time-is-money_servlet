package com.mylearning.timeismoney.dao;

import com.mylearning.timeismoney.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    List<User> findAllProxy();

    Optional<User> findById(int id);

    Optional<User> findByLogin(String login);
}
