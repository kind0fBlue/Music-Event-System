package com.unimelb.swen90007.sda1_musicsystem.service.impl;

import com.unimelb.swen90007.sda1_musicsystem.Mapper.VenueMapper;
import com.unimelb.swen90007.sda1_musicsystem.UoW.VenueUoW;
import com.unimelb.swen90007.sda1_musicsystem.domain.Venue;
import com.unimelb.swen90007.sda1_musicsystem.service.VenueService;

import java.util.ArrayList;
import java.util.List;

public class VenueServiceImpl implements VenueService {
    private VenueMapper venueMapper = new VenueMapper();
    private VenueUoW venueUoW = new VenueUoW();

    @Override
    public void addVenue(Venue venue) {
        venueUoW.newCurrent();
        venueUoW.getCurrent().registerNew(venue);
        venueUoW.getCurrent().commit();
    }

    @Override
    public List<Venue> availableVenues() {
        List<Venue> resultList = this.venueMapper.availableVenues();

        List<Integer> venueIdList = new ArrayList<>();
        for(Venue v:resultList) {
            //System.out.println(v.toString());
            venueIdList.add(v.getVenueId());
        }
        return resultList;
    }

}
