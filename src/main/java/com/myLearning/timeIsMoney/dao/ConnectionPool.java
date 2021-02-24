package com.mylearning.timeismoney.dao;

import java.sql.Connection;

/**
 * Ensures return Connection method
 */
public interface ConnectionPool {

    Connection getConnection();
}
