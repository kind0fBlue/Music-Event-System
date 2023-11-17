package com.unimelb.swen90007.sda1_musicsystem;

import com.unimelb.swen90007.sda1_musicsystem.Mapper.OrderMapper;
import com.unimelb.swen90007.sda1_musicsystem.Mapper.TicketMapper;
import com.unimelb.swen90007.sda1_musicsystem.domain.EventVenuesVo;
import com.unimelb.swen90007.sda1_musicsystem.domain.Order;
import com.unimelb.swen90007.sda1_musicsystem.domain.Ticket;
import com.unimelb.swen90007.sda1_musicsystem.service.EventService;
import com.unimelb.swen90007.sda1_musicsystem.service.OrderService;
import com.unimelb.swen90007.sda1_musicsystem.service.TicketService;
import com.unimelb.swen90007.sda1_musicsystem.service.impl.EventServiceImpl;
import com.unimelb.swen90007.sda1_musicsystem.service.impl.OrderServiceImpl;
import com.unimelb.swen90007.sda1_musicsystem.service.impl.TicketServiceImpl;
import com.unimelb.swen90007.sda1_musicsystem.servlet.EventViewServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@WebServlet(name = "viewallbooking", value = "/viewallbooking")
public class ViewAllBookingServlet extends HttpServlet {
    private OrderMapper orderMapper = new OrderMapper();
    private OrderService orderService = new OrderServiceImpl();
    private TicketMapper ticketMapper = new TicketMapper();
    private TicketService ticketService = new TicketServiceImpl();
    private EventService eventService = new EventServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        String eventId = req.getParameter("eventId");
        Connection conn = JDBCtest.connectRender();

        switch (method) {
            case "display":
                resp.setContentType("text/html");
                req.setAttribute("eventId",eventId);
                List<Ticket> tickets = ticketMapper.findTicketByEventId(Integer.parseInt(eventId));
                req.setAttribute("list",getUniqueOrderList(tickets));
                req.getRequestDispatcher("viewbookings.jsp").forward(req, resp);
                break;
            case "delete":
                Integer eventid = Integer.parseInt(req.getParameter("eventId"));
                Integer orderId = Integer.parseInt(req.getParameter("orderId"));
                resp.setContentType("text/html");

                List<Order> orderList = orderService.findOrder(null, orderId);
                List<EventVenuesVo> event = this.eventService.findEvent(null, eventid);
                Integer venueId = event.get(0).getVenueId();

                try {
                    this.orderService.deleteOrder(orderId);

                    List<Ticket> ticket = orderList.get(0).getTicket();
                    ticketService.deleteTicket(orderId);
                    EventViewServlet.updateVenueCapacity(ticket, event);
                }
                catch (Exception e) {
                   e.printStackTrace();
                }
                finally {
                    resp.sendRedirect("viewallbooking?method=display&eventId=" + eventId);
                }
                break;
            case "detail":
                orderId = Integer.parseInt(req.getParameter("orderId"));
                System.out.println("orderId at detail is: "+orderId);
                resp.setContentType("text/html");
                req.setAttribute("list",ticketMapper.findTicketByOrderId(orderId));
                req.getRequestDispatcher("viewticket.jsp").forward(req, resp);
                break;
        }

    }


    private List<Order> getUniqueOrderList(List<Ticket> ticketList) {
        List<Order> resultOrderList = new ArrayList<>();
        HashSet<Integer> resultList = new HashSet<>();

        for(Ticket ticket: ticketList) {
            resultList.add(ticket.getOrderId());
        }

        for (Integer i: resultList) {
            List<Order> orderList = orderService.findOrder(null,i);
            resultOrderList.add(orderList.get(0));
        }
        return resultOrderList;
    }
}
