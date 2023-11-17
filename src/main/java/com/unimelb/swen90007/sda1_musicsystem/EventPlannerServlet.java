package com.unimelb.swen90007.sda1_musicsystem;

import com.unimelb.swen90007.sda1_musicsystem.Mapper.EventAssignedMapper;
import com.unimelb.swen90007.sda1_musicsystem.Mapper.EventMapper;
import com.unimelb.swen90007.sda1_musicsystem.UoW.AssignEventUoW;
import com.unimelb.swen90007.sda1_musicsystem.domain.*;
import com.unimelb.swen90007.sda1_musicsystem.service.EventService;
import com.unimelb.swen90007.sda1_musicsystem.service.OrderService;
import com.unimelb.swen90007.sda1_musicsystem.service.TicketService;
import com.unimelb.swen90007.sda1_musicsystem.service.VenueService;
import com.unimelb.swen90007.sda1_musicsystem.service.impl.EventServiceImpl;
import com.unimelb.swen90007.sda1_musicsystem.service.impl.OrderServiceImpl;
import com.unimelb.swen90007.sda1_musicsystem.service.impl.TicketServiceImpl;
import com.unimelb.swen90007.sda1_musicsystem.service.impl.VenueServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@WebServlet(name = "eventplanner", value = "/eventplanner")
public class EventPlannerServlet extends HttpServlet {
    private EventService eventService= new EventServiceImpl();
    private VenueService venueService = new VenueServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private TicketService ticketService = new TicketServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        String user = req.getParameter("user");
        EventAssignedMapper events = new EventAssignedMapper();
        AssignEventUoW assignEventUoW=new AssignEventUoW();
        EventMapper eventMapper = new EventMapper();
        Connection connection;
        connection = JDBCtest.connectRender();
        HttpSession httpSession = req.getSession(true);
        switch (method) {
            case "navigate":
                resp.setContentType("text/html");
                req.setAttribute("user",user);
                //req.setAttribute("list", events.gettNotAssignedList(user).getEvents());
                req.setAttribute("eventList", events.getAssignedList(user).getEvents());
                req.getRequestDispatcher("/eventPlannerPage.jsp").forward(req, resp);
                break;
            case "display":
                req.setAttribute("availableList", this.venueService.availableVenues());
                req.setAttribute("user",user);
                req.getRequestDispatcher("/eventPlannerCreateEvent.jsp").forward(req, resp);
            case "add":



                resp.setContentType("text/html");
                user = req.getParameter("user");
                String eventName = req.getParameter("eventName");
                String artist = req.getParameter("artistName");
                String starttime = req.getParameter("startTime");
                String endtime = req.getParameter("endTime");
                int vipPrice = Integer.parseInt(req.getParameter("vipPrice"));
                int moshPrice  = Integer.parseInt(req.getParameter("moshPrice"));
                int standingPrice = Integer.parseInt(req.getParameter("standingPrice"));
                int seatedPrice = Integer.parseInt(req.getParameter("seatedPrice"));

                int vipCapacity = Integer.parseInt(req.getParameter("vipCapacity"));
                int moshCapacity  = Integer.parseInt(req.getParameter("moshCapacity"));
                int standingCapacity = Integer.parseInt(req.getParameter("standingCapacity"));
                int seatedCapacity = Integer.parseInt(req.getParameter("seatedCapacity"));
                int totalCapacity = vipCapacity+moshCapacity+standingCapacity+seatedCapacity;


                Timestamp startTime = Timestamp.valueOf(starttime);
                Timestamp endTime = Timestamp.valueOf(endtime);

                int venueId = Integer.parseInt(req.getParameter("venueId"));
                boolean overlap =checkOverlap(startTime,endTime,venueId);





                try {
                    LockManagerEx.getInstance().acquireLock(venueId, httpSession.getId());

                    if (overlap) {
                        req.setAttribute("user", user);
                        req.setAttribute("availableList", this.venueService.availableVenues());
                        req.setAttribute("errorMessage", "time overlapped");
                        req.getRequestDispatcher("/eventPlannerCreateEvent.jsp").forward(req, resp);
                    } else {
                        Event newEvent = new Event(eventName, artist, totalCapacity, endTime, startTime, venueId, vipPrice, moshPrice, standingPrice, seatedPrice, vipCapacity, moshCapacity, standingCapacity, seatedCapacity, 1);
                        this.eventService.addEvent(newEvent);
                        //this.eventService.takeVenue(venueId);
                        Integer eventAssign = Integer.valueOf(eventMapper.getEventId(eventName));
                        AssignEventUoW.newCurrent();
                        //System.out.println("in the add the user name is: " + user);
                        EventAssigend event = new EventAssigend(eventAssign, user);
                        AssignEventUoW.getCurrent().commit();

                        resp.sendRedirect("eventplanner?method=navigate&user=" + user);
                    }
                }

                catch (RuntimeException e) {
                    // Lock could not be acquired, handle accordingly
                    req.setAttribute("errorMessage", "Failed to acquire lock, please try again later.");
                    req.getRequestDispatcher("/eventPlannerCreateEvent.jsp").forward(req, resp);
                } finally {
                    // Ensure lock is released even if an exception is thrown in the try block
                    LockManagerEx.getInstance().releaseLock(venueId, httpSession.getId());
                }
                break;

            case "delete":
                Integer idInt = Integer.parseInt(req.getParameter("id"));
                Integer venueIdInt = Integer.parseInt(req.getParameter("venueId"));
                try {
                    //LockManagerEx.getInstance().acquireLock(idInt, httpSession.getId());


                    List<EventVenuesVo> event = this.eventService.findEvent(null, idInt, connection);

                    assignEventUoW.newCurrent();
                    assignEventUoW.registerDel(idInt);
                    assignEventUoW.commit();
                    eventService.delete(idInt);
                    LockManagerEx.getInstance().releaseLock(idInt, httpSession.getId());

                    List<Ticket> tickets = ticketService.findTicketByEventId(idInt);
                    List<Integer> orders = new ArrayList<>();
                    for (Ticket t : tickets) {
                        if (!orders.contains(t.getOrderId())) {
                            orders.add(t.getOrderId());
                        }
                    }

                    for (Integer orderId : orders) {
                        try {
                            orderService.deleteOrder(orderId);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        ticketService.deleteTicket(orderId);
                    }
                }
                catch (Exception e) {
                    throw new RuntimeException("the event can not be deleted");
                }
                finally {
                    //this.eventService.releaseVenue(venueIdInt);
                    resp.sendRedirect("eventplanner?method=navigate&user="+user);
                }

                break;
            case "update":
                Integer id = Integer.parseInt(req.getParameter("id"));
                System.out.println("Version number is xxxx: "+this.eventService.findEvent(null, id).get(0).getVersion());
                req.setAttribute("THE_EVENT", this.eventService.findEvent(null, id).get(0));
                req.setAttribute("venue", Integer.parseInt(req.getParameter("venueId")));
                req.getRequestDispatcher("/updateEvent.jsp").forward(req,resp);
                break;
            case "UpdateEvent":
                int update_id = Integer.parseInt(req.getParameter("eventId"));
                String update_eventName = req.getParameter("eventName");
                String update_artistName = req.getParameter("artistName");
                //int update_totalCapacity = Integer.parseInt(req.getParameter("totalCapacity"));
                Timestamp update_startTime = Timestamp.valueOf(req.getParameter("startTime"));
                Timestamp update_endTime = Timestamp.valueOf(req.getParameter("endTime"));
                int vip = Integer.parseInt(req.getParameter("vipPrice"));
                int mosh  = Integer.parseInt(req.getParameter("moshPrice"));
                int standing = Integer.parseInt(req.getParameter("standingPrice"));
                int seated = Integer.parseInt(req.getParameter("seatedPrice"));
                int version = Integer.parseInt(req.getParameter("version"));

                int update_venueId = Integer.parseInt(req.getParameter("venue"));
                boolean update_overlap =checkOverlap(update_startTime,update_endTime,update_venueId,update_id);

                if (update_overlap) {
                    EventVenuesVo theEvent = new EventVenuesVo(update_id, update_eventName, update_artistName, update_endTime, update_startTime, vip,mosh,standing,seated);
                    req.setAttribute("THE_EVENT", theEvent);
                    req.setAttribute("venue", update_venueId);
                    req.setAttribute("errorMessage","time overlapped");
                    req.getRequestDispatcher("/updateEvent.jsp").forward(req,resp);
                }
                else {

                    Event theEvent = new Event(update_id, update_eventName, update_artistName, update_endTime, update_startTime, vip,mosh,standing,seated,version);

                    try {

                        LockManagerEx.getInstance().acquireLock(update_venueId, httpSession.getId());

                        connection.setAutoCommit(false);
                        this.eventService.updateEventWithPrice(theEvent, connection);
                        theEvent.setVersion(version+1);
                        System.out.println("Version number is xxxx: "+theEvent.getVersion());
                    }

                    catch (EventMapper.OptimisticLockException e) {
                        try {
                            connection.rollback(); // roll back the transaction on conflict
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        req.setAttribute("errorMessage", "Update failed because data has changed. Please retry.");
                        // return to update form or handle error otherwise
                    }

                    catch (SQLException e) {
                        try {
                            System.out.println("Rolling back transaction: " + e.getMessage());
                            connection.rollback();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }finally {
                        try {
                            LockManagerEx.getInstance().releaseLock(update_venueId, httpSession.getId());
                            connection.setAutoCommit(true);


                        }catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }


                    resp.sendRedirect("eventplanner?method=navigate&user="+req.getRemoteUser());

                }


            break;
        }
    }

    private boolean checkOverlap(Timestamp starttime, Timestamp endtime,int venueId) {
        List<Event> list=this.eventService.getEventList(venueId);
        for (Event event:list) {
            int startResult = starttime.compareTo(event.getEndTime());
            int endResult = endtime.compareTo(event.getStartTime());
            if (startResult<0 && endResult>0) {
                return true;
            }
        }
        return false;
    }

    private boolean checkOverlap(Timestamp starttime, Timestamp endtime,int venueId, int eventId) {
        List<Event> list=this.eventService.getEventList(venueId);
        list.removeIf(o->o.getId().equals(eventId));
        for (Event event:list) {
            int startResult = starttime.compareTo(event.getEndTime());
            int endResult = endtime.compareTo(event.getStartTime());
            if (startResult<0 && endResult>0) {
                return true;
            }
        }
        return false;
    }
}
