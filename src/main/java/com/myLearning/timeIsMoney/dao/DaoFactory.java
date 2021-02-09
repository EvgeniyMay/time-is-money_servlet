package com.myLearning.timeIsMoney.dao;

import com.myLearning.timeIsMoney.dao.impl.JdbcConnectionPool;

public interface DaoFactory {

    public ActivityDao createActivityDao(JdbcConnectionPool jdbcConnectionPool);
}
