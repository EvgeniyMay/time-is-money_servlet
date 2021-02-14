package com.mylearning.timeismoney.dao.impl;

import com.mylearning.timeismoney.dao.*;

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
    public UserDao createUserDao() {
        return new JdbcUserDao(connectionPool.getConnection());
    }

    @Override
    public ActivityDao createActivityDao() {
        return new JdbcActivityDao(connectionPool.getConnection());
    }

    @Override
    public MissionDao createMissionDao() {
        //ToDo
        // Refactor
        return new JdbcMissionDao(connectionPool.getConnection(), this);
    }
}
