package com.unimelb.swen90007.sda1_musicsystem;

import com.unimelb.swen90007.sda1_musicsystem.domain.Event;
import com.unimelb.swen90007.sda1_musicsystem.service.EventService;
import com.unimelb.swen90007.sda1_musicsystem.service.impl.EventServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "View Event", value = "/viewevents")
public class ViewEventServlet extends HttpServlet {
    private EventService eventService = new EventServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        String user=req.getParameter("user");

        String theCommand = req.getParameter("command");

        if (method.equals("display")) {
            req.setAttribute("list", this.eventService.getList());
            req.getRequestDispatcher("viewevents.jsp").forward(req, resp);
        }
        else if (method.equals("delete")) {
            Integer idInt = Integer.parseInt(req.getParameter("id"));
            Integer venueId;
            venueId = Integer.parseInt(req.getParameter("venueId"));
            this.eventService.delete(idInt);
            //this.eventService.releaseVenue(venueId);
            resp.sendRedirect("viewevents?method=display");
        }
        else if (method.equals("filter")) {
            req.setAttribute("list", this.eventService.getComingList());
            req.getRequestDispatcher("viewevents.jsp").forward(req, resp);
            resp.sendRedirect("viewevents?method=display");

        }
        else if (method.equals("LOAD")) {
            String eventId = req.getParameter("eventId");
            Event theEvent = null;
            try {
                theEvent = this.eventService.getEvent(eventId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            req.setAttribute("THE_EVENT", theEvent);
            req.getRequestDispatcher("updateEvent.jsp").forward(req, resp);



        }


    }
}
