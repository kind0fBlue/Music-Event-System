package com.unimelb.swen90007.sda1_musicsystem.UoW;

import com.unimelb.swen90007.sda1_musicsystem.Mapper.EventMapper;
import com.unimelb.swen90007.sda1_musicsystem.Mapper.VenueMapper;
import com.unimelb.swen90007.sda1_musicsystem.domain.Event;
import com.unimelb.swen90007.sda1_musicsystem.domain.Venue;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VenueUoW {
    private static ThreadLocal current = new ThreadLocal();

    private List<Venue> newObjects = new ArrayList<Venue>();
    private List<Venue> ditryObjects = new ArrayList<Venue>();
    private List<Integer> delObjects = new ArrayList<Integer>();

    public static void newCurrent() {
        setCurrent(new VenueUoW());
    }

    public static void setCurrent(VenueUoW uow) {
        current.set(uow);
    }

    public static VenueUoW getCurrent() {
        return (VenueUoW) current.get();
    }


    public void registerNew(Venue obj) {
        Assert.assertNotNull("Venue is null!", obj.getVenueName());
        Assert.assertTrue("Event is dirty!", !ditryObjects.contains(obj));
        Assert.assertTrue("Event is deleted!", !delObjects.contains(obj));
        Assert.assertTrue("Event has been added!", !newObjects.contains(obj));
        newObjects.add(obj);
    }

    public void registerClean(Event obj) {
        Assert.assertNotNull("Event is null!", obj.getId());
    }


    public void commit() {
        for (Venue obj : newObjects) {
            VenueMapper.insertVenue(obj);
        }
        
    }
}
