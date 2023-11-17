package com.unimelb.swen90007.sda1_musicsystem.domain;

import com.unimelb.swen90007.sda1_musicsystem.Mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
