package com.mylearning.timeismoney.dao;

import java.util.List;
import java.util.Optional;

/**
 * Ensures realization of basic database operations
 */
public interface GenericDao<T> extends AutoCloseable {

    boolean create (T entity);

    List<T> findAll();

    boolean update(T entity);

    void close();
}
