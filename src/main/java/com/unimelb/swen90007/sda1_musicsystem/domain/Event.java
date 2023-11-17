package com.unimelb.swen90007.sda1_musicsystem.domain;



import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.Date;

public class Event {
    private Integer id;
    private Integer version;
    private String eventName;
    private String artistName;
    private int totalCapacity;
    private Timestamp endTime;
    private Timestamp startTime;
    private Integer venueId;
    private Integer vipPrice;
    private Integer moshPrice;
    private Integer standingPrice;
    private Integer seatedPrice;
    private Integer vipCapacity;
    private Integer moshCapacity;
    private Integer standingCapacity;
    private Integer seatedCapacity;



    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event(String eventName, String artistName, int totalCapacity, Timestamp endTime, Timestamp startTime, Integer venueId, Integer vipPrice, Integer moshPrice, Integer standingPrice, Integer seatedPrice, Integer vipCapacity, Integer moshCapacity, Integer standingCapacity, Integer seatedCapacity, Integer version) {

        this.eventName = eventName;
        this.artistName = artistName;
        this.totalCapacity = totalCapacity;
        this.endTime = endTime;
        this.startTime = startTime;
        this.venueId = venueId;
        this.vipPrice = vipPrice;
        this.moshPrice = moshPrice;
        this.standingPrice = standingPrice;
        this.seatedPrice = seatedPrice;
        this.vipCapacity = vipCapacity;
        this.moshCapacity = moshCapacity;
        this.standingCapacity = standingCapacity;
        this.seatedCapacity = seatedCapacity;
        this.version = version;
    }

    public Event(Integer id, String eventName, String artistName, int totalCapacity, Timestamp endTime, Timestamp startTime, Integer venueId, Integer version) {
        this.id = id;
        this.eventName = eventName;
        this.artistName = artistName;
        this.totalCapacity = totalCapacity;
        this.endTime = endTime;
        this.startTime = startTime;
        this.venueId = venueId;
        this.version = version;
    }

    public Event(Integer id, String eventName) {
        this.id = id;
        this.eventName = eventName;
    }

    public Event(String eventName, String artistName, int totalCapacity, Timestamp endTime, Timestamp startTime, Integer venueId) {
        this.eventName = eventName;
        this.artistName = artistName;
        this.totalCapacity = totalCapacity;
        this.endTime = endTime;
        this.startTime = startTime;
        this.venueId = venueId;
    }

    public Event(String eventName, String artistName, Integer totalcapacity, Timestamp endTime, Timestamp startTime, Integer venueId, Integer vipPrice, Integer moshPrice, Integer standingPrice, Integer seatedPrice, Integer vipCapacity, Integer moshCapacity, Integer standingCapacity, Integer seatedCapacity) {
        this.eventName = eventName;
        this.artistName = artistName;
        this.totalCapacity=totalcapacity;
        this.endTime = endTime;
        this.startTime = startTime;
        this.venueId = venueId;
        this.vipPrice = vipPrice;
        this.moshPrice = moshPrice;
        this.standingPrice = standingPrice;
        this.seatedPrice = seatedPrice;
        this.vipCapacity = vipCapacity;
        this.moshCapacity = moshCapacity;
        this.standingCapacity = standingCapacity;
        this.seatedCapacity = seatedCapacity;
    }

    public Event(String eventname, String artistname, Integer totalcapacity, Timestamp endtime, Timestamp starttime, Integer venueId, Integer vipprice, Integer moshprice, Integer standingprice, Integer seatedprice) {
        this.eventName = eventname;
        this.artistName = artistname;
        this.totalCapacity = totalcapacity;
        this.endTime = endtime;
        this.startTime = starttime;
        this.vipPrice = vipprice;
        this.moshPrice = moshprice;
        this.standingPrice = standingprice;
        this.seatedPrice = seatedprice;
        this.venueId=venueId;
    }

    public Event(Integer id, String eventName, String artistName, Timestamp endTime, Timestamp startTime, Integer vipPrice, Integer moshPrice, Integer standingPrice, Integer seatedPrice) {
        this.id = id;
        this.eventName = eventName;
        this.artistName = artistName;
        this.endTime = endTime;
        this.startTime = startTime;
        this.vipPrice = vipPrice;
        this.moshPrice = moshPrice;
        this.standingPrice = standingPrice;
        this.seatedPrice = seatedPrice;
    }

    public Event(Integer id, String eventName, String artistName, Timestamp endTime, Timestamp startTime, Integer vipPrice, Integer moshPrice, Integer standingPrice, Integer seatedPrice,Integer version) {
        this.id = id;
        this.eventName = eventName;
        this.artistName = artistName;
        this.endTime = endTime;
        this.startTime = startTime;
        this.vipPrice = vipPrice;
        this.moshPrice = moshPrice;
        this.standingPrice = standingPrice;
        this.seatedPrice = seatedPrice;
        this.version = version;
    }

    public Event(Integer id, Integer vipCapacity, Integer moshCapacity, Integer standingCapacity, Integer seatedCapacity) {
        this.id = id;
        this.vipCapacity = vipCapacity;
        this.moshCapacity = moshCapacity;
        this.standingCapacity = standingCapacity;
        this.seatedCapacity = seatedCapacity;
    }


    public String getEventName() {
        return eventName;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }



    public Timestamp getStartTime() {
        return startTime;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Integer getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(Integer vipPrice) {
        this.vipPrice = vipPrice;
    }

    public Integer getMoshPrice() {
        return moshPrice;
    }

    public void setMoshPrice(Integer moshPrice) {
        this.moshPrice = moshPrice;
    }

    public Integer getStandingPrice() {
        return standingPrice;
    }

    public void setStandingPrice(Integer standingPrice) {
        this.standingPrice = standingPrice;
    }

    public Integer getSeatedPrice() {
        return seatedPrice;
    }

    public void setSeatedPrice(Integer seatedPrice) {
        this.seatedPrice = seatedPrice;
    }


    public Integer getVipCapacity() {
        return vipCapacity;
    }

    public void setVipCapacity(Integer vipCapacity) {
        this.vipCapacity = vipCapacity;
    }

    public Integer getMoshCapacity() {
        return moshCapacity;
    }

    public void setMoshCapacity(Integer moshCapacity) {
        this.moshCapacity = moshCapacity;
    }

    public Integer getStandingCapacity() {
        return standingCapacity;
    }

    public void setStandingCapacity(Integer standingCapacity) {
        this.standingCapacity = standingCapacity;
    }

    public Integer getSeatedCapacity() {
        return seatedCapacity;
    }

    public void setSeatedCapacity(Integer seatedCapacity) {
        this.seatedCapacity = seatedCapacity;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}


