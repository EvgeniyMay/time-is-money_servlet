package com.myLearning.timeIsMoney.dao.impl;

import com.myLearning.timeIsMoney.dao.ActivityDao;
import com.myLearning.timeIsMoney.dao.ConnectionPool;
import com.myLearning.timeIsMoney.dao.DaoFactory;
import com.myLearning.timeIsMoney.dao.UserDao;

public class JdbcDaoFactory implements DaoFactory {

    private static JdbcDaoFactory jdbcDaoFactory;
    private JdbcDaoFactory() {}
    public static JdbcDaoFactory getInstance() {
        if(jdbcDaoFactory == null) {
            synchronized (JdbcDaoFactory.class) {
                if(jdbcDaoFactory == null) {
                    jdbcDaoFactory = new JdbcDaoFactory();
                }
            }
        }
        return jdbcDaoFactory;
    }

    private final ConnectionPool connectionPool = JdbcConnectionPool.getInstance();


    @Override
    public ActivityDao createActivityDao() {
        return new JdbcActivityDao(connectionPool.getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao(connectionPool.getConnection());
    }
}
