package com.unimelb.swen90007.sda1_musicsystem.service.impl;

import com.unimelb.swen90007.sda1_musicsystem.JDBCtest;
import com.unimelb.swen90007.sda1_musicsystem.Mapper.TicketMapper;
import com.unimelb.swen90007.sda1_musicsystem.UoW.TicketUoW;
import com.unimelb.swen90007.sda1_musicsystem.domain.Ticket;
import com.unimelb.swen90007.sda1_musicsystem.service.TicketService;
import com.unimelb.swen90007.sda1_musicsystem.domain.Price;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TicketServiceImpl implements TicketService {
    TicketMapper ticketMapper = new TicketMapper();
    @Override
    public void addTicket(Ticket ticket, Connection conn) {
        TicketUoW.newCurrent();
        TicketUoW.getCurrent().registerNew(ticket, conn);
        TicketUoW.getCurrent().commit();
    }

    @Override
    public void deleteTicket(Integer orderId) {
        TicketUoW.newCurrent();
        TicketUoW.getCurrent().registerDel(orderId);
        TicketUoW.getCurrent().commit();
    }

    @Override
    public List<Ticket> findTicketByEventId(Integer eventId){
        return ticketMapper.findTicketByEventId(eventId);
    }
}
