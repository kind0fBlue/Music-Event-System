package com.unimelb.swen90007.sda1_musicsystem.domain;

import com.unimelb.swen90007.sda1_musicsystem.UoW.UserUoW;

public class UserWithRole {

    private String username;
    private String password;
    private String role;

    public UserWithRole(String username,String password, String role) {
        this.username = username;
        this.password=password;
        this.role = role;
        if (UserUoW.getCurrent() != null) UserUoW.getCurrent().registerNew(this);
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
}
