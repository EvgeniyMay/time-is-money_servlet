package com.mylearning.timeismoney.service;

import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.dao.UserDao;
import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.exception.DaoException;
import com.mylearning.timeismoney.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * class for all operation with user
 */
public class UserService {
    /**
     * Logger for logging important information
     */
    private final Logger logger = LogManager.getLogger();

    /**
     * Dao for dao creating
     */
    private final DaoFactory daoFactory;

    public UserService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        logger.info("User service created");
    }


    /**
     * get all users
     * @return List of users
     */
    public List<User> findAll() {
        try(UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll();
        } catch (DaoException e) {
            logger.warn("Find all error");
            throw new ServiceException("find ll error", e);
        }
    }

    /**
     * find user by login or thrown excepting if it was not found
     * @param login of user
     * @return user
     */
    public User findByLoginProxy(String login) {
        try(UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByLoginProxy(login)
                    .orElseThrow(() -> new DaoException("User not found"));
        } catch (DaoException e) {
            logger.warn("User with login {} not found", login);
            throw new ServiceException("User not found", e);
        }
    }

    /**
     * find user by login or thrown excepting if it was not found
     * @param id of user
     * @return user
     */
    public User findById(int id) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findById(id)
                    .orElseThrow(() -> new DaoException("User not found"));
        } catch (DaoException e) {
            logger.warn("User with id {} not found", id);
            throw new ServiceException("User not found", e);
        }
    }

    /**
     * Crate new user
     * @param user to create
     * @return result of creation
     */
    public boolean create(User user) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.create(user);
        } catch (DaoException e) {
            logger.warn("Create error");
            throw new ServiceException("Create error", e);
        }
    }
}
