package com.unimelb.swen90007.sda1_musicsystem.UoW;

import com.unimelb.swen90007.sda1_musicsystem.Mapper.EventAssignedMapper;
import com.unimelb.swen90007.sda1_musicsystem.Mapper.UserMapper;
import com.unimelb.swen90007.sda1_musicsystem.Mapper.UserRoleMapper;
import com.unimelb.swen90007.sda1_musicsystem.domain.EventAssigend;
import com.unimelb.swen90007.sda1_musicsystem.domain.Order;
import com.unimelb.swen90007.sda1_musicsystem.domain.UserWithRole;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AssignEventUoW {
    private static ThreadLocal current= new ThreadLocal();

    private List<EventAssigend> newObjects=new ArrayList<EventAssigend>();
    private List<EventAssigend> ditryObjects=new ArrayList<EventAssigend>();
    private List<Integer> delObjects=new ArrayList<Integer>();

    public static void newCurrent(){
        setCurrent(new AssignEventUoW());
    }

    public static void setCurrent(AssignEventUoW uow){
        current.set(uow);
    }

    public static AssignEventUoW getCurrent(){
        return (AssignEventUoW) current.get();
    }


    public void registerNew(EventAssigend obj){
        Assert.assertNotNull("Username is null!",obj.getUsername());
        Assert.assertTrue("Assignment is dirty!",!ditryObjects.contains(obj));
        Assert.assertTrue("Assignment is deleted!",!delObjects.contains(obj.getEventId()));
        Assert.assertTrue("Assignment has been added!",!newObjects.contains(obj));
        newObjects.add(obj);
    }

    public void registerDel(int id) {
        Assert.assertNotNull("Event is null!",id);
        Optional<EventAssigend> obj = newObjects.stream().filter(o->o.getEventId().equals(id)).findAny();
        if(obj.isPresent()){
            newObjects.remove(obj);
        }
        if(!ditryObjects.stream().anyMatch(o->o.getEventId().equals(id))){
            delObjects.add(id);
        }
    }

    public void commit(){
        for(EventAssigend obj:newObjects){
            EventAssignedMapper.insertUser(obj);
        }
        for(Integer id:delObjects){
            EventAssignedMapper.deleteEventAssignedById(id);
        }
    }
}
