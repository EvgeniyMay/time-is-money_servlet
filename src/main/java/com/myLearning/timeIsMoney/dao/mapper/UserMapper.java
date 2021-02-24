package com.mylearning.timeismoney.dao.mapper;

import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * work with User and ResultSet
 */
public class UserMapper {

    public static User getFromResultSet(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .id(resultSet.getInt("user_id"))
                .login(resultSet.getString("user_login"))
                .password(resultSet.getString("user_password"))
                .role(Role.valueOf(resultSet.getString("user_role")))
                .missions(new ArrayList<>())
                .build();
    }

    public static User getProxyFromResultSet(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .id(resultSet.getInt("user_id"))
                .login(resultSet.getString("user_login"))
                .build();
    }

    public static User getUserDetailsFromResultSet(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .id(resultSet.getInt("user_id"))
                .login(resultSet.getString("user_login"))
                .password(resultSet.getString("user_password"))
                .role(Role.valueOf(resultSet.getString("user_role")))
                .build();
    }

    public static void basicFillStatement(PreparedStatement ps, User user) throws SQLException {
        ps.setString(1, user.getLogin());
        ps.setString(2, user.getPassword());
        ps.setString(3, Role.USER.toString());
    }
}
