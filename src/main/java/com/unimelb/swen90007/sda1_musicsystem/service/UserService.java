package com.unimelb.swen90007.sda1_musicsystem.service;

import com.unimelb.swen90007.sda1_musicsystem.domain.User;
import com.unimelb.swen90007.sda1_musicsystem.domain.UserRole;

import java.util.List;

public interface UserService {
    public void addUser(User user);
    public List<User> getList();

}
