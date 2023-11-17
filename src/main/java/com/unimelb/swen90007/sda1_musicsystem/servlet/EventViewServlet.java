package com.unimelb.swen90007.sda1_musicsystem.servlet;

import com.unimelb.swen90007.sda1_musicsystem.JDBCtest;
import com.unimelb.swen90007.sda1_musicsystem.LockManagerEx;
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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet(value = "/eventView")
public class EventViewServlet extends HttpServlet {

    private static EventService eventService = new EventServiceImpl();

    private static VenueService venueService = new VenueServiceImpl();

    private OrderService orderService = new OrderServiceImpl();

    private TicketService ticketService = new TicketServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        String eventName = req.getParameter("eventName");
        Connection conn = JDBCtest.connectRender();

        if (method == null) {
            // Handle this case, for instance by sending a default response or logging an error.
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Method parameter is missing.");
            return;
        }

        HttpSession session = req.getSession();

        String user = (String) session.getAttribute("user");
        if (method.equals("select")) {
            req.setAttribute("eventVenuesVos", this.eventService.findEvent(eventName, null));
            if (Objects.nonNull(eventName)) {
                req.setAttribute("eventName", eventName);
            }
            req.getRequestDispatcher("EventsView.jsp").forward(req, resp);
        } else if (method.equals("findEventByEventId")) {
            Integer eventId = Integer.valueOf(req.getParameter("eventId"));
            req.setAttribute("eventVenuesVo", this.eventService.findEvent(null, eventId).get(0));
            LockManagerEx.getInstance().releaseLock(eventId,"editEvent");
            req.getRequestDispatcher("BuyEventsView.jsp").forward(req, resp);
        } else if (method.equals("buyEvents")) {
            Integer eventId = Integer.valueOf(req.getParameter("eventId"));

            Integer buyVipcapacity = Integer.valueOf(req.getParameter("buyVipcapacity"));
            Integer buyMoshcapacity = Integer.valueOf(req.getParameter("buyMoshcapacity"));
            Integer buyStandingcapacity = Integer.valueOf(req.getParameter("buyStandingcapacity"));
            Integer buySeatedcapacity = Integer.valueOf(req.getParameter("buySeatedcapacity"));
            Integer totalAmoumt = Integer.valueOf(req.getParameter("totalAmoumt"));
            if(totalAmoumt==0){
                String msg="Please book more than 1 ticket!";
                req.setAttribute("errorMsg", msg);
                req.setAttribute("eventVenuesVo", this.eventService.findEvent(null, eventId).get(0));
                req.getRequestDispatcher("BuyEventsView.jsp").forward(req, resp);
                return;
            }

            try {
                conn.setAutoCommit(false);
                conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

                EventVenuesVo eventVenuesVo = this.eventService.findEvent(null, eventId,conn).get(0);
                Integer vipcapacity = eventVenuesVo.getVipcapacity();
                Integer moshcapacity = eventVenuesVo.getMoshcapacity();
                Integer standingcapacity = eventVenuesVo.getStandingcapacity();
                Integer seatedcapacity = eventVenuesVo.getSeatedcapacity();
                //System.out.println("1111");

                Order order = orderService.findOrderDescOrderId(conn);
                int orderId = order == null ? 1 : order.getOrderId() + 1;
                Order newOrder= new Order(orderId, user, totalAmoumt);
                if(vipcapacity>=buyVipcapacity&&moshcapacity>=buyMoshcapacity&&standingcapacity>=buyStandingcapacity&&seatedcapacity>=buySeatedcapacity) {
                    orderService.addOrder(newOrder, conn);
                    //System.out.println("22222");
                }
                int ticketFlag=0;
                /***********Embedded value************/
                if (Objects.nonNull(buyVipcapacity) && buyVipcapacity != 0 && vipcapacity>=buyVipcapacity) {
                    Price vipPrice = new Price(eventVenuesVo.getVipprice() * buyVipcapacity, "AUD");
                    Ticket ticket=new Ticket(vipPrice, Section.VIP.toString(), eventId, orderId);
                    ticketService.addTicket(ticket, conn);
                    //System.out.println("3333");
                    ticketFlag++;
                }
                if (Objects.nonNull(buyMoshcapacity) && buyMoshcapacity != 0 && moshcapacity>=buyMoshcapacity) {
                    Price moshPrice = new Price(eventVenuesVo.getMoshprice() * buyMoshcapacity, "AUD");
                    Ticket ticket=new Ticket(moshPrice, Section.MOSH.toString(), eventId, orderId);
                    ticketService.addTicket(ticket, conn);
                    ticketFlag++;
                }
                if (Objects.nonNull(buyStandingcapacity) && buyStandingcapacity != 0 && standingcapacity>= buyStandingcapacity) {
                    Price standingPrice = new Price(eventVenuesVo.getStandingprice() * buyStandingcapacity, "AUD");
                    Ticket ticket=new Ticket(standingPrice, Section.STANDING.toString(), eventId, orderId);
                    ticketService.addTicket(ticket, conn);
                    ticketFlag++;
                }
                if (Objects.nonNull(buySeatedcapacity) && buySeatedcapacity != 0 && seatedcapacity>=buySeatedcapacity) {
                    Price seatedPrice = new Price(eventVenuesVo.getSeatedprice() * buySeatedcapacity, "AUD");
                    Ticket ticket=new Ticket(seatedPrice, Section.SEATED.toString(), eventId, orderId);
                        ticketService.addTicket(ticket, conn);
                    ticketFlag++;
                }
                /***********Embedded value************/
                if(vipcapacity>=buyVipcapacity&&moshcapacity>=buyMoshcapacity&&standingcapacity>=buyStandingcapacity&&seatedcapacity>=buySeatedcapacity)
                {
                    Event event=new Event(eventId,eventVenuesVo.getVipcapacity()-buyVipcapacity,eventVenuesVo.getMoshcapacity()-buyMoshcapacity,eventVenuesVo.getStandingcapacity()-buyStandingcapacity,eventVenuesVo.getSeatedcapacity()-buySeatedcapacity);
                    this.eventService.updateEventWithCapacity(event, conn);
                    //System.out.println("44444");
                }
                if(ticketFlag!=buySeatedcapacity+buyVipcapacity+buyStandingcapacity+buyMoshcapacity){
                    throw new RuntimeException();
                }

                LockManagerEx.getInstance().releaseLock(eventId,"editEvent");

            } catch (Exception e ) {
                try {
                    //e.printStackTrace();
                    conn.rollback();
                    System.out.println("Rolling back transaction");
                    String msg="Booking error occurs. Please refresh the event page and retry!";
                    req.setAttribute("errorMsg", msg);
                    req.setAttribute("eventVenuesVo", this.eventService.findEvent(null, eventId).get(0));
                    req.getRequestDispatcher("BuyEventsView.jsp").forward(req, resp);
                    return;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            List<Order> orderList = orderService.findOrder(user, null);
            req.setAttribute("orderList", orderList);
            req.getRequestDispatcher("OrderView.jsp").forward(req, resp);

        } else if (method.equals("findOrder")) {
            List<Order> orderList = orderService.findOrder(user, null);
            req.setAttribute("orderList", orderList);
            req.getRequestDispatcher("OrderView.jsp").forward(req, resp);
        } else if (method.equals("findOrderByOrderId")) {
            Integer orderId = Integer.valueOf(req.getParameter("orderId"));
            List<Order> orderList = orderService.findOrder(null, orderId);
            req.setAttribute("tickets", orderList.get(0).getTicket());
            Integer eventId = orderList.get(0).getTicket().get(0).getEventId();
            List<EventVenuesVo> event = this.eventService.findEvent(null, eventId);
            //System.out.println("eventId!"+ eventId);
            req.setAttribute("eventName", event.get(0).getEventname());
            req.getRequestDispatcher("TicketView.jsp").forward(req, resp);
        } else if (method.equals("deleteOrderByOrderId")) {
            Integer orderId = Integer.valueOf(req.getParameter("orderId"));
            List<Order> orderList = orderService.findOrder(null, orderId);
            Integer eventId = orderList.get(0).getTicket().get(0).getEventId();
            List<EventVenuesVo> event = this.eventService.findEvent(null, eventId);

            Integer venueId = event.get(0).getVenueId();
            try {
                this.orderService.deleteOrder(orderId);

                List<Ticket> ticket = orderList.get(0).getTicket();
                this.ticketService.deleteTicket(orderId);

                updateVenueCapacity(ticket, event);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                List<Order> orders = orderService.findOrder(user, null);
                req.setAttribute("orderList", orders);
                req.getRequestDispatcher("OrderView.jsp").forward(req, resp);
            }

        }else if (method.equals("admin")) {
            List<Order> orderList = orderService.findOrder(null, null);
            req.setAttribute("orderList", orderList);
            req.getRequestDispatcher("OrderView.jsp").forward(req, resp);
        }
    }

    public static void updateVenueCapacity(List<Ticket> ticket, List<EventVenuesVo> events){
        int buyVipcapacity = 0;
        int buyMoshcapacity = 0;
        int buyStandingcapacity = 0;
        int buySeatedcapacity = 0;

        EventVenuesVo event=events.get(0);

        for (Ticket t : ticket) {
            int ticketPrice = t.getPrice().getAmount();
            if (t.getSection().equals(Section.VIP.toString())) {
                buyVipcapacity = ticketPrice / event.getVipprice();
            }
            if (t.getSection().equals(Section.MOSH.toString())) {
                buyMoshcapacity = ticketPrice / event.getMoshprice();
            }
            if (t.getSection().equals(Section.STANDING.toString())) {
                buyStandingcapacity = ticketPrice / event.getStandingprice();
            }
            if (t.getSection().equals(Section.SEATED.toString())) {
                buySeatedcapacity = ticketPrice / event.getSeatedprice();
            }
        }

        Event eventUpdate=new Event(event.getEventId(),event.getVipcapacity()+buyVipcapacity,event.getMoshcapacity()+buyMoshcapacity,event.getStandingcapacity()+buyStandingcapacity,event.getSeatedcapacity()+buySeatedcapacity);
        eventService.updateEventWithCapacity(eventUpdate);
    }
}
