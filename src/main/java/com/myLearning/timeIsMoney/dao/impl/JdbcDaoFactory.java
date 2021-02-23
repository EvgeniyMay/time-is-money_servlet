package com.mylearning.timeismoney.dao.impl;

import com.mylearning.timeismoney.dao.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JdbcDaoFactory implements DaoFactory {

    private final static Logger logger = LogManager.getLogger();

    private static JdbcDaoFactory jdbcDaoFactory;
    private JdbcDaoFactory() {}
    public static JdbcDaoFactory getInstance() {
        if(jdbcDaoFactory == null) {
            synchronized (JdbcDaoFactory.class) {
                if(jdbcDaoFactory == null) {
                    jdbcDaoFactory = new JdbcDaoFactory();
                    logger.info("JdbcDaoFactory created");
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
        return new JdbcMissionDao(connectionPool.getConnection());
    }
}
