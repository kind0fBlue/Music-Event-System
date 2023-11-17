package com.unimelb.swen90007.sda1_musicsystem.domain;

public class UserRole {
    private String username;
    private String role;

    public UserRole(String username, String role) {
        super();
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

}
