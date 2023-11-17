package com.unimelb.swen90007.sda1_musicsystem.service;

import com.unimelb.swen90007.sda1_musicsystem.domain.Venue;

import java.util.List;

public interface VenueService {
    public void addVenue(Venue venue);
    public List<Venue> availableVenues();

}
