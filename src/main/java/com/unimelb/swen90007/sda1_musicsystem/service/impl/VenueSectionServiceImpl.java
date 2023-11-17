package com.unimelb.swen90007.sda1_musicsystem.service.impl;

import com.unimelb.swen90007.sda1_musicsystem.Mapper.SectionMapper;
import com.unimelb.swen90007.sda1_musicsystem.domain.VenueSection;
import com.unimelb.swen90007.sda1_musicsystem.service.VenueSectionService;

import java.util.ArrayList;
import java.util.List;

public class VenueSectionServiceImpl implements VenueSectionService {
    private SectionMapper sectionMapper = new SectionMapper();
    @Override
    public void addSections(int vip, int mosh, int standing, int seated, int venueId) {
        List<VenueSection> venueSections = new ArrayList<>();
        if (vip!=0) {
            VenueSection venueSectionVip = new VenueSection("vip",vip,venueId);
            venueSections.add(venueSectionVip);
        }
        if (mosh!=0) {
            VenueSection venueSectionMosh = new VenueSection("mosh",mosh,venueId);
            venueSections.add(venueSectionMosh);
        }
        if (standing!=0) {
            VenueSection venueSectionStanding = new VenueSection("standing",standing,venueId);
            venueSections.add(venueSectionStanding);
        }
        if (seated!=0) {
            VenueSection venueSectionSeated = new VenueSection("seated",seated,venueId);
            venueSections.add(venueSectionSeated);
        }

        for (VenueSection venueSection: venueSections) {
            Integer result =  this.sectionMapper.insertSection(venueSection);
            if (result!=1) {
                throw new RuntimeException("add section failed");
            }
            else  {
                System.out.println("add sections successfully");
            }
        }

    }
}
