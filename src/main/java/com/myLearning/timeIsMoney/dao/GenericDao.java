package com.myLearning.timeIsMoney.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {

    boolean create (T entity);

    Optional<T> findById(int id);

    List<T> findAll();

    boolean update(T entity);

    void close();
}
