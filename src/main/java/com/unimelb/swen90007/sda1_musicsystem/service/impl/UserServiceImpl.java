package com.unimelb.swen90007.sda1_musicsystem.service.impl;

import com.unimelb.swen90007.sda1_musicsystem.Mapper.UserMapper;
import com.unimelb.swen90007.sda1_musicsystem.domain.User;
import com.unimelb.swen90007.sda1_musicsystem.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserMapper userMapper=new UserMapper();
    @Override
    public void addUser(User user) {
        Integer addResult = this.userMapper.insertUser(user);
        if (addResult != 1) {
            throw new RuntimeException("Create user failed");
        }
        else {
            System.out.println("Create user successfully");
        }
    }

    @Override
    public List<User> getList() {
        return this.userMapper.getList();
    }

}
