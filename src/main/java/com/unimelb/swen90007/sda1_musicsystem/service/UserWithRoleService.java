package com.unimelb.swen90007.sda1_musicsystem.service;

import com.unimelb.swen90007.sda1_musicsystem.domain.User;
import com.unimelb.swen90007.sda1_musicsystem.domain.UserWithRole;

import java.util.List;

public interface UserWithRoleService {
    public List<UserWithRole> getList();

}
