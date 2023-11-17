package com.unimelb.swen90007.sda1_musicsystem.UoW;

import com.unimelb.swen90007.sda1_musicsystem.Mapper.OrderMapper;
import com.unimelb.swen90007.sda1_musicsystem.Mapper.TicketMapper;
import com.unimelb.swen90007.sda1_musicsystem.domain.Event;
import com.unimelb.swen90007.sda1_musicsystem.domain.Order;
import com.unimelb.swen90007.sda1_musicsystem.domain.Ticket;
import org.junit.Assert;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class TicketUoW {
    private static ThreadLocal current= new ThreadLocal();

    private List<Ticket> newObjects=new ArrayList<Ticket>();
    private List<Ticket> ditryObjects=new ArrayList<Ticket>();
    private List<Integer> delObjects=new ArrayList<Integer>();
    private HashMap<Integer, Connection> connObjects=new HashMap<>();

    public static void newCurrent(){
        setCurrent(new TicketUoW());
    }

    public static void setCurrent(TicketUoW uow){
        current.set(uow);
    }

    public static TicketUoW getCurrent(){
        return (TicketUoW) current.get();
    }


    public void registerNew(Ticket obj, Connection conn){
        Assert.assertNotNull("Order is null!",obj.getOrderId());
        Assert.assertTrue("Ticket is dirty!",!ditryObjects.contains(obj));
        Assert.assertTrue("Ticket is deleted!",!delObjects.contains(obj.getOrderId()));
        Assert.assertTrue("Ticket has been added!",!newObjects.contains(obj));
        newObjects.add(obj);
        connObjects.put(obj.getOrderId(),conn);
    }


    public void registerDel(int orderId) {
        Assert.assertNotNull("Event is null!",orderId);
        Optional<Ticket> obj = newObjects.stream().filter(o->o.getOrderId().equals(orderId)).findAny();
        if(obj.isPresent()){
            newObjects.remove(obj);
            return;
        }
        if(!ditryObjects.stream().anyMatch(o->o.getOrderId().equals(orderId))){
            delObjects.add(orderId);
        }
    }


    public void registerClean(Ticket obj){
        Assert.assertNotNull("Ticket is null!",obj.getTicketId());
    }



    public void commit(){
        for(Ticket obj:newObjects){
            TicketMapper.addTicket(obj.getPrice(),obj.getSection(), obj.getEventId(), obj.getOrderId(), connObjects.get(obj.getOrderId()));
        }
        for(Integer orderId:delObjects){
            TicketMapper.deleteTicket(orderId);
        }
    }
}
