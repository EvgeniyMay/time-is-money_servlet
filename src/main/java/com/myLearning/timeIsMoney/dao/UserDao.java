package com.mylearning.timeismoney.dao;

import com.mylearning.timeismoney.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Ensures realization of user access object
 */
public interface UserDao extends GenericDao<User> {

    /**
     * find Optional object of user by id
     * @param id of user to find
     * @return optional object of finding result
     */
    Optional<User> findById(int id);

    /**
     * find Optional object of user by id
     * @param login of user to find
     * @return optional object of finding result
     */
    Optional<User> findByLoginProxy(String login);
}
