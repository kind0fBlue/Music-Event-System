package com.unimelb.swen90007.sda1_musicsystem.service;

import com.unimelb.swen90007.sda1_musicsystem.domain.Event;
import com.unimelb.swen90007.sda1_musicsystem.domain.EventVenuesVo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EventService {
    public void addEvent(Event event);
    public List<Event> getList();
    public List<Event> getEventList(Integer venueId);
    public void delete(Integer id);
    public void takeVenue(Integer venueId);

    List<EventVenuesVo> findEvent(String eventName,Integer eventId);
    public List<EventVenuesVo> findEvent(String eventName, Integer eventId, Connection conn);
    public void releaseVenue(Integer venueId);


    public List<Event> getComingList();

    Event getEvent(String eventID);

    public void updateEventWithPrice(Event theEvent, Connection conn);
    public void updateEventWithCapacity(Event theEvent);
    public void updateEventWithCapacity(Event theEvent, Connection conn);
}
