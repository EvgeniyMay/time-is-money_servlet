package com.myLearning.timeIsMoney.dao;

import java.sql.Connection;

public interface ConnectionPool {

    public Connection getConnection();
}
