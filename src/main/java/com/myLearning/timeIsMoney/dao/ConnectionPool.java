package com.mylearning.timeismoney.dao;

import java.sql.Connection;

public interface ConnectionPool {

    public Connection getConnection();
}
