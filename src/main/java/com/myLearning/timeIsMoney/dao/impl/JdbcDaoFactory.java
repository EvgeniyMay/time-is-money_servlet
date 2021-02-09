package com.myLearning.timeIsMoney.dao.impl;

import com.myLearning.timeIsMoney.dao.ActivityDao;
import com.myLearning.timeIsMoney.dao.DaoFactory;

public class JdbcDaoFactory implements DaoFactory {

    private static JdbcDaoFactory jdbcDaoFactory;
    private JdbcDaoFactory() { }
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

    @Override
    public ActivityDao createActivityDao(JdbcConnectionPool jdbcConnectionPool) {
        return new JdbcActivityDao(jdbcConnectionPool);
    }

}
