package com.mylearning.timeismoney.dao.mapper;

import com.mylearning.timeismoney.entity.User;
import com.mylearning.timeismoney.entity.enums.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserMapper {

    public static User getFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("user_id"));
        user.setLogin(resultSet.getString("user_login"));
        user.setPassword(resultSet.getString("user_password"));
        user.setRole(Role.valueOf(resultSet.getString("user_role")));

        user.setMissions(new ArrayList<>());

        return user;
    }

    public static void basicFillStatement(PreparedStatement ps, User user) throws SQLException {
        ps.setString(1, user.getLogin());
        ps.setString(2, user.getPassword());
        ps.setString(3, Role.USER.toString());
    }
}
