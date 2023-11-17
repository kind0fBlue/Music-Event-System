package com.unimelb.swen90007.sda1_musicsystem.Mapper;

import com.unimelb.swen90007.sda1_musicsystem.JDBCtest;
import com.unimelb.swen90007.sda1_musicsystem.domain.User;
import com.unimelb.swen90007.sda1_musicsystem.domain.UserRole;
import com.unimelb.swen90007.sda1_musicsystem.domain.UserWithRole;

import java.sql.*;
import java.util.*;

public class UserMapper {
    public static Integer insertUser(User user) {
        Connection connection = JDBCtest.connectRender();
        String sql = "insert into users(username, password) values(?,?)";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public List<User> getList() {
        Connection connection = JDBCtest.connectRender();
        String sql = "select * from users";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> resultList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String userName = resultSet.getString(1);
                String password = resultSet.getString(2);
                resultList.add(new User(userName,password));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return resultList;
    }

    public static User getUserInfo(UserWithRole user){
        return new User(user.getUsername(), user.getPassword());
    }

}
