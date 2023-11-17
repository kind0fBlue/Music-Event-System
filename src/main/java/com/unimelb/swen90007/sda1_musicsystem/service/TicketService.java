package com.unimelb.swen90007.sda1_musicsystem.service;
import com.unimelb.swen90007.sda1_musicsystem.domain.Price;
import com.unimelb.swen90007.sda1_musicsystem.domain.Ticket;

import java.sql.Connection;
import java.util.List;

public interface TicketService {
    /***********Embedded value************/
    void addTicket(Ticket ticket, Connection conn);
    /***********Embedded value************/

    void deleteTicket(Integer ticketId);
    public List<Ticket> findTicketByEventId(Integer eventId);
}
