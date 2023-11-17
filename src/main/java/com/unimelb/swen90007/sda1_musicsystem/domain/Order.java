package com.unimelb.swen90007.sda1_musicsystem.domain;

import com.unimelb.swen90007.sda1_musicsystem.Mapper.TicketMapper;

import java.util.List;

public class Order {

    private Integer orderId;

    private String userName;

    private Integer totalPrice;

    private String date;

    private List<Ticket> ticket;

    public List<Ticket> getTicket() {
        if(ticket==null){
            TicketMapper ticketMapper=new TicketMapper();
            ticket = ticketMapper.findTicketByOrderId(orderId);
        }
        return ticket;
    }

    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Order(Integer orderId, String userName, Integer totalPrice, String date, List<Ticket> ticket) {
        this.orderId = orderId;
        this.userName = userName;
        this.totalPrice = totalPrice;
        this.date = date;
        this.ticket = ticket;
    }

    public Order(Integer orderId, String userName, Integer totalPrice) {
        this.orderId = orderId;
        this.userName = userName;
        this.totalPrice = totalPrice;
    }

    public Order() {

    }
}
