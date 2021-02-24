package com.mylearning.timeismoney.service;

import com.mylearning.timeismoney.dao.DaoFactory;
import com.mylearning.timeismoney.dao.UserDao;
import com.mylearning.timeismoney.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class UserServiceTest {

    private final DaoFactory mockDaoFactory = Mockito.mock(DaoFactory.class);
    private final UserDao mockUserDao = Mockito.mock(UserDao.class);
    private final UserService userService = new UserService(mockDaoFactory);

    @Before
    public void createDao() {
        Mockito.when(mockDaoFactory.createUserDao())
                .thenReturn(mockUserDao);
    }

    @Test
    public void findAll() {
        List<User> users = Arrays.asList(
                new User.Builder().id(0).build(),
                new User.Builder().id(1).build());

        Mockito.when(mockUserDao.findAll())
                .thenReturn(users);

        List<User> result = userService.findAll();

        assertEquals(users, result);
    }

    @Test
    public void findByLoginProxy() {
        User user = new User.Builder().id(0).build();

        Mockito.when(mockUserDao.findByLoginProxy("test"))
                .thenReturn(Optional.of(user));

        User result = userService.findByLoginProxy("test");

        assertEquals(user, result);
    }

    @Test
    public void findById() {
        User user = new User.Builder().id(0).build();

        Mockito.when(mockUserDao.findById(5))
                .thenReturn(Optional.of(user));

        User result = userService.findById(0);

        assertEquals(user, result);
    }

    @Test
    public void create() {
        User user = new User.Builder().id(0).build();

        Mockito.when(mockUserDao.create(user))
                .thenReturn(true);

        boolean created = userService.create(user);

        assertTrue(created);
    }
}