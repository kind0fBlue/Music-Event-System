package com.unimelb.swen90007.sda1_musicsystem;

import com.unimelb.swen90007.sda1_musicsystem.Mapper.EventAssignedMapper;
import com.unimelb.swen90007.sda1_musicsystem.UoW.AssignEventUoW;
import com.unimelb.swen90007.sda1_musicsystem.UoW.UserUoW;
import com.unimelb.swen90007.sda1_musicsystem.domain.Event;
import com.unimelb.swen90007.sda1_musicsystem.domain.EventAssigend;
import com.unimelb.swen90007.sda1_musicsystem.domain.UserWithRole;
import com.unimelb.swen90007.sda1_musicsystem.service.impl.EventServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
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

import com.unimelb.swen90007.sda1_musicsystem.service.EventService;

@WebServlet(name = "assignevents", value = "/assignevents")
public class AssignEventServlet extends HttpServlet {
    private EventService eventService = new EventServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        String user = req.getParameter("user");
        switch (method) {
            case "navigate":
                resp.setContentType("text/html");
                EventAssignedMapper events=new EventAssignedMapper();
                EventAssigend ep=new EventAssigend(user);
                req.setAttribute("user",user);
                req.setAttribute("list", events.gettNotAssignedList(user).getEvents());
                req.setAttribute("eventList", ep.getEvents());
                req.getRequestDispatcher("/assignevents.jsp").forward(req, resp);
                break;
            case "assign":
                resp.setContentType("text/html");
                Integer eventAssign = Integer.valueOf(req.getParameter("eventAssign"));

                AssignEventUoW.newCurrent();
                EventAssigend event=new EventAssigend(eventAssign,user);
                AssignEventUoW.getCurrent().commit();
                resp.sendRedirect("assignevents?method=navigate&user="+user);
                break;
        }
    }
}
