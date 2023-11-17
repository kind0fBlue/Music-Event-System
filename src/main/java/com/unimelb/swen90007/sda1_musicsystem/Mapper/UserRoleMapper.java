package com.unimelb.swen90007.sda1_musicsystem.Mapper;

import com.unimelb.swen90007.sda1_musicsystem.JDBCtest;
import com.unimelb.swen90007.sda1_musicsystem.domain.User;
import com.unimelb.swen90007.sda1_musicsystem.domain.UserRole;
import com.unimelb.swen90007.sda1_musicsystem.domain.UserWithRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleMapper{
    public static Integer insertUserRole(UserRole userRole) {
        Connection connection = JDBCtest.connectRender();
        String sql = "insert into userrole(username, role) values(?,?)";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            System.out.println(sql);
            statement = connection.prepareStatement(sql);
            statement.setString(1, userRole.getUsername());
            statement.setString(2, userRole.getRole());
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

    public List<UserRole> getList() {
        Connection connection = JDBCtest.connectRender();
        String sql = "select * from userrole";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<UserRole> resultList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String userName = resultSet.getString(2);
                String role = resultSet.getString(1);
                resultList.add(new UserRole(userName,role));
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

    public static UserRole getUserRoleInfo(UserWithRole user){
        return new UserRole(user.getUsername(), user.getRole());
    }


    public static Integer updateUserRole(UserRole userRole) {
        Connection connection = JDBCtest.connectRender();
        String sql = "update userrole set role=? where username = ?";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(2, userRole.getUsername());
            statement.setString(1, userRole.getRole());
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
}
