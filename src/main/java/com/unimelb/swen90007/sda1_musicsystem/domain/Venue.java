package com.unimelb.swen90007.sda1_musicsystem.domain;

public class Venue {
    private Integer venueId;
    private String venueName;
    private String location;
    private boolean hasEvent;

    public Venue(String venueName, String location
                ) {
        this.venueName = venueName;
        this.location = location;

        this.hasEvent = false;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "venueId=" + venueId +
                ", venueName='" + venueName + '\'' +
                ", location='" + location + '\'' +
                ", hasEvent=" + hasEvent +
                '}';
    }

    public Venue(Integer venueId, String venueName, String location) {
        this.venueId = venueId;
        this.venueName = venueName;
        this.location = location;
        this.hasEvent = false;
    }

    public Venue(Integer venueId, String venueName, boolean hasEvent) {
        this.venueId = venueId;
        this.venueName = venueName;
        this.hasEvent = hasEvent;
    }

    public Venue(Integer venueId) {
        this.venueId = venueId;
    }

    public boolean isHasEvent() {
        return hasEvent;
    }

    public void setHasEvent(boolean hasEvent) {
        this.hasEvent = hasEvent;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
