package com.unimelb.swen90007.sda1_musicsystem;

import com.unimelb.swen90007.sda1_musicsystem.domain.Venue;
import com.unimelb.swen90007.sda1_musicsystem.service.VenueSectionService;
import com.unimelb.swen90007.sda1_musicsystem.service.VenueService;
import com.unimelb.swen90007.sda1_musicsystem.service.impl.VenueServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;

@WebServlet(name = "CreateVenue", value = "/CreateVenue")
public class CreateVenueServlet extends HttpServlet {
    private VenueService venueService = new VenueServiceImpl();
    //private VenueSectionService venueSectionService = new VenueSectionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("createvenue.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html");

            System.out.println("Post method in CreateVenueServlet");
            String venue = request.getParameter("venueName");
            //String num = request.getParameter("totalSections");
            String location = request.getParameter("location");
//            int vipCapacity = Integer.parseInt(request.getParameter("vipCapacity"));
//            int moshCapacity = Integer.parseInt(request.getParameter("moshCapacity"));
//            int standingCapacity = Integer.parseInt(request.getParameter("standingCapacity"));
//            int seatedCapacity = Integer.parseInt(request.getParameter("seatedCapacity"));
            Venue newVenue = new Venue(venue, location);
            this.venueService.addVenue(newVenue);
            response.sendRedirect("Navigate.jsp");
    }
}
