package com.unimelb.swen90007.sda1_musicsystem.UoW;

import com.unimelb.swen90007.sda1_musicsystem.Mapper.EventMapper;
import com.unimelb.swen90007.sda1_musicsystem.Mapper.UserMapper;
import com.unimelb.swen90007.sda1_musicsystem.Mapper.UserRoleMapper;
import com.unimelb.swen90007.sda1_musicsystem.domain.Event;
import com.unimelb.swen90007.sda1_musicsystem.domain.UserWithRole;
import org.junit.Assert;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class EventUoW {
    private static ThreadLocal current = new ThreadLocal();

    private List<Event> newObjects = new ArrayList<Event>();
    private List<Event> ditryObjects = new ArrayList<Event>();
    private List<Integer> delObjects = new ArrayList<Integer>();
    private HashMap<Integer, Connection> connObjects=new HashMap<>();

    public static void newCurrent() {
        setCurrent(new EventUoW());
    }

    public static void setCurrent(EventUoW uow) {
        current.set(uow);
    }

    public static EventUoW getCurrent() {
        return (EventUoW) current.get();
    }


    public void registerNew(Event obj) {
        Assert.assertNotNull("Event is null!", obj.getEventName());
        Assert.assertTrue("Event is dirty!", !ditryObjects.contains(obj));
        Assert.assertTrue("Event is deleted!", !delObjects.contains(obj.getId()));
        Assert.assertTrue("Event has been added!", !newObjects.contains(obj));
        newObjects.add(obj);
    }

    public void registerDirty(Event obj) {
        Assert.assertNotNull("Event is null!", obj.getId());
        Assert.assertTrue("Event is deleted!", !delObjects.contains(obj.getId()));
        //System.out.println("update event!!!!");
        if (!ditryObjects.contains(obj) && !newObjects.contains(obj)) {
            //System.out.println("add dirty!!!!");
            ditryObjects.add(obj);
        }
    }

    public void registerDirty(Event obj, Connection conn) {
        Assert.assertNotNull("Event is null!", obj.getId());
        Assert.assertTrue("Event is deleted!", !delObjects.contains(obj.getId()));
        //System.out.println("update event!!!!");
        if (!ditryObjects.contains(obj) && !newObjects.contains(obj)) {
            //System.out.println("add dirty!!!!");
            ditryObjects.add(obj);
            connObjects.put(obj.getId(), conn);

        }
    }

    public void registerDel(int id) {
        Assert.assertNotNull("Event is null!", id);
        Optional<Event> obj = newObjects.stream().filter(o -> o.getId().equals(id)).findAny();
        if (obj.isPresent()) {
            newObjects.remove(obj);
            return;
        }
        obj = ditryObjects.stream().filter(o -> o.getId().equals(id)).findAny();
        if (obj.isPresent()) {
            ditryObjects.remove(obj);
        }
        if (!ditryObjects.stream().anyMatch(o -> o.getId().equals(id))) {
            delObjects.add(id);
        }
    }


    public void registerClean(Event obj) {
        Assert.assertNotNull("Event is null!", obj.getId());
    }


    public void commit() {
        for (Event obj : newObjects) {
            EventMapper.insertEvent(obj);
        }
        for (Event obj : ditryObjects) {
            if((obj.getVipCapacity()!=null ||obj.getMoshCapacity()!=null || obj.getSeatedCapacity()!=null ||obj.getStandingCapacity()!=null) && connObjects.containsKey(obj.getId())) {
                EventMapper.updateEventWithCapacity(obj,connObjects.get(obj.getId()));
            }
            else if (obj.getVipCapacity()!=null ||obj.getMoshCapacity()!=null || obj.getSeatedCapacity()!=null ||obj.getStandingCapacity()!=null){
                EventMapper.updateEventWithCapacity(obj);
            }
            else  if (connObjects.containsKey(obj.getId()))
            {
                EventMapper.updateEventWithPrice(obj,connObjects.get(obj.getId()));
            }
        }
        for (Integer id : delObjects) {
            EventMapper.deleteById(id);
        }
    }
}