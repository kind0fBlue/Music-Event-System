package com.unimelb.swen90007.sda1_musicsystem.service.impl;

import com.unimelb.swen90007.sda1_musicsystem.JDBCtest;
import com.unimelb.swen90007.sda1_musicsystem.Mapper.EventMapper;
import com.unimelb.swen90007.sda1_musicsystem.UoW.EventUoW;
import com.unimelb.swen90007.sda1_musicsystem.domain.Event;
import com.unimelb.swen90007.sda1_musicsystem.domain.EventVenuesVo;
import com.unimelb.swen90007.sda1_musicsystem.service.EventService;

import java.sql.*;
import java.util.List;

public class EventServiceImpl implements EventService {
    private EventMapper eventMapper = new EventMapper();

    @Override
    public void addEvent(Event event) {
        EventUoW.newCurrent();
        EventUoW.getCurrent().registerNew(event);
        EventUoW.getCurrent().commit();
    }

    @Override
    public List<Event> getList() {
        return this.eventMapper.getList();
    }

    @Override
    public List<Event> getEventList(Integer venueId) {
        return this.eventMapper.getEventList(venueId);
    }

    public List<Event> getComingList() {
        return this.eventMapper.getComingList();
    }

    @Override
    public void delete(Integer id) {
        EventUoW.newCurrent();
        EventUoW.getCurrent().registerDel(id);
        EventUoW.getCurrent().commit();
    }

    @Override
    public void takeVenue(Integer venueId) {
        Integer takeVenueResult = this.eventMapper.venueHasEvent(venueId);
        if (takeVenueResult != 1) {
            throw new RuntimeException("can not change the venue to has event");
        }

    }

    @Override
    public void releaseVenue(Integer venueId) {
        Integer takeVenueResult = this.eventMapper.releaseVenue(venueId);
        if (takeVenueResult != 1) {
            throw new RuntimeException("can not change the venue to not have event");
        }
    }

    @Override
    public List<EventVenuesVo> findEvent(String eventName, Integer eventId) {
        return this.eventMapper.findEvent(eventName, eventId);
    }

    @Override
    public List<EventVenuesVo> findEvent(String eventName, Integer eventId, Connection conn) {
        return this.eventMapper.findEvent(eventName, eventId,conn);
    }

    @Override
    public void updateEventWithPrice(Event theEvent, Connection conn){
        EventUoW.newCurrent();
        EventUoW.getCurrent().registerDirty(theEvent, conn);
        EventUoW.getCurrent().commit();
    }

    @Override
    public void updateEventWithCapacity(Event theEvent){
        EventUoW.newCurrent();
        EventUoW.getCurrent().registerDirty(theEvent);
        EventUoW.getCurrent().commit();
    }

    @Override
    public void updateEventWithCapacity(Event theEvent, Connection conn){
        EventUoW.newCurrent();
        EventUoW.getCurrent().registerDirty(theEvent, conn);
        EventUoW.getCurrent().commit();
    }

    @Override
    public Event getEvent(String eventID){
        return this.eventMapper.getEvent(eventID);
    }

}
