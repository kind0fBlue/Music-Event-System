package com.unimelb.swen90007.sda1_musicsystem.Mapper;

import com.unimelb.swen90007.sda1_musicsystem.JDBCtest;
import com.unimelb.swen90007.sda1_musicsystem.domain.UserRole;
import com.unimelb.swen90007.sda1_musicsystem.domain.UserWithRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserWithRoleMapper{
    public List<UserWithRole> getList() {
        Connection connection = JDBCtest.connectRender();
        String sql = "select * from users";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<UserWithRole> resultList = new ArrayList<>();
        List<UserRole> roles=new UserRoleMapper().getList();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String userName = resultSet.getString(1);
                String password = resultSet.getString(2);
                System.out.println(roles.size());
                System.out.println(roles.get(1).getUsername());
                String role = roles.stream().filter(o->o.getUsername().equals(userName)).findFirst().get().getRole();
                resultList.add(new UserWithRole(userName,password,role));
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
}
