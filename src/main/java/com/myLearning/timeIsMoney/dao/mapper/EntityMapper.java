package com.myLearning.timeIsMoney.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityMapper<T> {

    T getFromResultSet (ResultSet resultSet) throws SQLException;

}
