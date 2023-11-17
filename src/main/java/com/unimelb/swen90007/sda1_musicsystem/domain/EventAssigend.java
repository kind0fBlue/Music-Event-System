package com.unimelb.swen90007.sda1_musicsystem.domain;

import com.unimelb.swen90007.sda1_musicsystem.Mapper.EventAssignedMapper;
import com.unimelb.swen90007.sda1_musicsystem.Mapper.EventMapper;
import com.unimelb.swen90007.sda1_musicsystem.Mapper.UserMapper;
import com.unimelb.swen90007.sda1_musicsystem.UoW.AssignEventUoW;
import com.unimelb.swen90007.sda1_musicsystem.UoW.UserUoW;

import java.util.List;

public class EventAssigend {
    private Integer eventId;
    private String username;
    private List<Event> events;

    public EventAssigend(String username, List<Event> events) {
        this.username = username;
        this.events = events;
    }

    public EventAssigend(String username) {
        this.username = username;
        this.events = null;
    }

    public EventAssigend(Integer eventId, String username) {
        this.eventId = eventId;
        this.username = username;
        if (AssignEventUoW.getCurrent() != null) AssignEventUoW.getCurrent().registerNew(this);
    }

    public Integer getEventId() {
        return eventId;
    }

    public String getUsername() {
        return username;
    }

    public List<Event> getEvents() {
        if(this.events==null){
            events= EventAssignedMapper.getAssigned(this.username);
        }
        return events;
    }
}
