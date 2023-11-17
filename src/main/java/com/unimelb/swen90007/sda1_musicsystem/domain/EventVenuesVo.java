package com.unimelb.swen90007.sda1_musicsystem.domain;

import java.sql.Timestamp;

public class EventVenuesVo {

    private Integer version;
    private String eventname;
    private String artistname;
    private Integer totalcapacity;
    private Timestamp endtime;
    private Timestamp starttime;
    private Integer eventId;
    private Integer venueId;

    private Integer vipprice;

    private Integer moshprice;

    private Integer standingprice;


    private String location;
    private Integer seatedprice;

    private String venuename;
    private Integer vipcapacity;
    private Integer moshcapacity;
    private Integer standingcapacity;
    private Integer seatedcapacity;
    private Boolean hasEvent;

    private Timestamp startTime;

    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    public Integer getVipprice() {
        return vipprice;
    }

    public void setVipprice(Integer vipprice) {
        this.vipprice = vipprice;
    }

    public Integer getMoshprice() {
        return moshprice;
    }

    public void setMoshprice(Integer moshprice) {
        this.moshprice = moshprice;
    }

    public Integer getStandingprice() {
        return standingprice;
    }

    public void setStandingprice(Integer standingprice) {
        this.standingprice = standingprice;
    }

    public Integer getSeatedprice() {
        return seatedprice;
    }

    public void setSeatedprice(Integer seatedprice) {
        this.seatedprice = seatedprice;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }

    public Integer getTotalcapacity() {
        return totalcapacity;
    }

    public void setTotalcapacity(Integer totalcapacity) {
        this.totalcapacity = totalcapacity;
    }


    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    public Timestamp getstarttime() {
        return starttime;
    }

    public void setstarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public String getVenuename() {
        return venuename;
    }

    public void setVenuename(String venuename) {
        this.venuename = venuename;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getVipcapacity() {
        return vipcapacity;
    }

    public void setVipcapacity(Integer vipcapacity) {
        this.vipcapacity = vipcapacity;
    }

    public Integer getMoshcapacity() {
        return moshcapacity;
    }

    public void setMoshcapacity(Integer moshcapacity) {
        this.moshcapacity = moshcapacity;
    }

    public Integer getStandingcapacity() {
        return standingcapacity;
    }

    public void setStandingcapacity(Integer standingcapacity) {
        this.standingcapacity = standingcapacity;
    }

    public Integer getSeatedcapacity() {
        return seatedcapacity;
    }

    public void setSeatedcapacity(Integer seatedcapacity) {
        this.seatedcapacity = seatedcapacity;
    }

    public Boolean getHasEvent() {
        return hasEvent;
    }

    public void setHasEvent(Boolean hasEvent) {
        this.hasEvent = hasEvent;
    }

    public EventVenuesVo() {

    }

    public Integer getVersion() {
        return version;
    }

    public EventVenuesVo(String eventname, String artistname, Integer totalcapacity, Timestamp endtime, Timestamp starttime, Integer eventId, Integer venueId, Integer vipprice, Integer moshprice, Integer standingprice, Integer seatedprice, String venuename, String location, Integer vipcapacity, Integer moshcapacity, Integer standingcapacity, Integer seatedcapacity, Boolean hasEvent, Timestamp startTime, Integer version) {
        this.version = version;
        this.eventname = eventname;
        this.artistname = artistname;
        this.totalcapacity = totalcapacity;
        this.endtime = endtime;
        this.starttime = starttime;
        this.eventId = eventId;
        this.venueId = venueId;
        this.vipprice = vipprice;
        this.moshprice = moshprice;
        this.standingprice = standingprice;
        this.seatedprice = seatedprice;
        this.venuename = venuename;
        this.location = location;
        this.vipcapacity = vipcapacity;
        this.moshcapacity = moshcapacity;
        this.standingcapacity = standingcapacity;
        this.seatedcapacity = seatedcapacity;
        this.hasEvent = hasEvent;
        this.startTime = startTime;
    }

    public EventVenuesVo(Integer eventId, String eventname, String artistname, Timestamp endtime, Timestamp startTime, Integer vipprice, Integer moshprice, Integer standingprice, Integer seatedprice) {
        this.eventname = eventname;
        this.artistname = artistname;
        this.endtime = endtime;
        this.eventId = eventId;
        this.vipprice = vipprice;
        this.moshprice = moshprice;
        this.standingprice = standingprice;
        this.seatedprice = seatedprice;
        this.startTime = startTime;
        this.starttime = startTime;
    }
}
