package com.mylearning.timeismoney.dao;

import com.mylearning.timeismoney.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    @Override
    boolean create(User entity);

    @Override
    Optional<User> findById(int id);

    Optional<User> findByLogin(String login);

    @Override
    List<User> findAll();

    @Override
    boolean update(User entity);

    @Override
    void close();
}
