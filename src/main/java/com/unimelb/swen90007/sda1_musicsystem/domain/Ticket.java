package com.unimelb.swen90007.sda1_musicsystem.domain;

public class Ticket {
    private String section;
    private Integer ticketId;
    private Integer eventId;
    private Integer orderId;

    /***********Embedded value************/
    private Price price;

    public Ticket(Price price, String section, Integer ticketId, Integer eventId, Integer orderId) {
        this.price = price;
        this.section = section;
        this.ticketId = ticketId;
        this.eventId = eventId;
        this.orderId = orderId;
    }
    public Ticket(Price price, String section, Integer eventId, Integer orderId) {
        this.price = price;
        this.section = section;
        this.eventId = eventId;
        this.orderId = orderId;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    /***********Embedded value************/

    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }
    public Integer getTicketId() {
        return ticketId;
    }
    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
    public Integer getEventId() {
        return eventId;
    }public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "section='" + section + '\'' +
                ", ticketId=" + ticketId +
                ", eventId=" + eventId +
                ", orderId=" + orderId +
                ", price=" + price +
                '}';
    }
}

