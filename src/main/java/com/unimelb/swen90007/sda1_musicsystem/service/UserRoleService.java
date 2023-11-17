package com.unimelb.swen90007.sda1_musicsystem.service;

import com.unimelb.swen90007.sda1_musicsystem.domain.Event;
import com.unimelb.swen90007.sda1_musicsystem.domain.UserRole;

import java.util.List;

public interface UserRoleService {
    public void addUserRole(UserRole userRole);
    public List<UserRole> getList();

}
