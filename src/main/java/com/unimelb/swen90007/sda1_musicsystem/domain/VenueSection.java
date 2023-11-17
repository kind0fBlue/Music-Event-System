package com.unimelb.swen90007.sda1_musicsystem.domain;

public class VenueSection {
    private int sectionId;
    private String type;
    private int capacity;
    private int venueId;

    public VenueSection(int sectionId, String type, int capacity, int venueId) {
        this.sectionId = sectionId;
        this.type = type;
        this.capacity = capacity;
        this.venueId = venueId;
    }

    public VenueSection(String type, int capacity, int venueId) {
        this.type = type;
        this.capacity = capacity;
        this.venueId = venueId;
    }

    @Override
    public String toString() {
        return "VenueSection{" +
                "sectionId=" + sectionId +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", venueId=" + venueId +
                '}';
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }
}
