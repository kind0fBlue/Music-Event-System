package com.unimelb.swen90007.sda1_musicsystem.SecurityCheck;

import com.unimelb.swen90007.sda1_musicsystem.JDBCtest;
import com.unimelb.swen90007.sda1_musicsystem.Mapper.UserRoleMapper;
import com.unimelb.swen90007.sda1_musicsystem.domain.UserWithRole;
import com.unimelb.swen90007.sda1_musicsystem.service.UserWithRoleService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserWithRoleServiceImpl implements UserDetailsService {
    @Override
    public UserDetails  loadUserByUsername(String username) throws UsernameNotFoundException {
        UserWithRole user = findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        UserBuilder builder = User.withUsername(user.getUsername());


        // Encode the password
        // Note there is no need to do this if the password is already encoded in the DB
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        builder.password(encodedPassword);
        builder.roles(user.getRole());

        return builder.build();
    }

    private UserWithRole findByUsername(String username) {
        Connection connection = JDBCtest.connectRender();
        String sql = "select * from users u left join userrole r on u.username = r.username where u.username='"+username+"'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        UserWithRole result = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String userName = resultSet.getString(1);
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                result=new UserWithRole(userName,password, role);
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
        return result;

    }
}
