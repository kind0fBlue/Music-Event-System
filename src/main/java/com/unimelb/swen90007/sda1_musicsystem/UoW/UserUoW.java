package com.unimelb.swen90007.sda1_musicsystem.UoW;

import com.unimelb.swen90007.sda1_musicsystem.Mapper.*;
import com.unimelb.swen90007.sda1_musicsystem.domain.*;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
public class UserUoW {
    private static ThreadLocal current= new ThreadLocal();

    private List<UserWithRole> newObjects=new ArrayList<UserWithRole>();
    private List<UserWithRole> ditryObjects=new ArrayList<UserWithRole>();
    private List<UserWithRole> delObjects=new ArrayList<UserWithRole>();

    public static void newCurrent(){
        setCurrent(new UserUoW());
    }

    public static void setCurrent(UserUoW uow){
        current.set(uow);
    }

    public static UserUoW getCurrent(){
        return (UserUoW) current.get();
    }


    public void registerNew(UserWithRole obj){
        Assert.assertNotNull("Username is null!",obj.getUsername());
        Assert.assertTrue("User is dirty!",!ditryObjects.contains(obj));
        Assert.assertTrue("User is deleted!",!delObjects.contains(obj));
        Assert.assertTrue("Username has been added!",!newObjects.contains(obj));
        newObjects.add(obj);
    }

    public void registerDirty(UserWithRole obj){
        Assert.assertNotNull("Username is null!",obj.getUsername());
        Assert.assertTrue("User is deleted!",!delObjects.contains(obj));
        if(!ditryObjects.contains(obj) && !newObjects.contains(obj) ){
            ditryObjects.add(obj);
        }
    }

    public void registerDel(UserWithRole obj) {
        Assert.assertNotNull("Username is null!",obj.getUsername());
        if(newObjects.remove(obj)) return;
        ditryObjects.remove(obj);
        if(!ditryObjects.contains(obj)){
            delObjects.add(obj);
        }
    }

    public void registerClean(UserWithRole obj){
        Assert.assertNotNull("Username is null!",obj.getUsername());
    }

    public void commit(){
        for(UserWithRole obj:newObjects){
            UserMapper.insertUser(UserMapper.getUserInfo(obj));
            UserRoleMapper.insertUserRole(UserRoleMapper.getUserRoleInfo(obj));
        }
        for(UserWithRole obj:ditryObjects){
            UserRoleMapper.updateUserRole(UserRoleMapper.getUserRoleInfo(obj));
        }
    }
}
