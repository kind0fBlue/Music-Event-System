package com.unimelb.swen90007.sda1_musicsystem.UoW;

import com.unimelb.swen90007.sda1_musicsystem.Mapper.EventMapper;
import com.unimelb.swen90007.sda1_musicsystem.Mapper.OrderMapper;
import com.unimelb.swen90007.sda1_musicsystem.domain.Event;
import com.unimelb.swen90007.sda1_musicsystem.domain.Order;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class OrderUoW {
    private static ThreadLocal current= new ThreadLocal();

    private List<Order> newObjects=new ArrayList<Order>();
    private List<Order> ditryObjects=new ArrayList<Order>();
    private List<Integer> delObjects=new ArrayList<Integer>();
    private HashMap<Integer, Connection> connObjects=new HashMap<>();

    public static void newCurrent(){
        setCurrent(new OrderUoW());
    }

    public static void setCurrent(OrderUoW uow){
        current.set(uow);
    }

    public static OrderUoW getCurrent(){
        return (OrderUoW) current.get();
    }


    public void registerNew(Order obj, Connection conn){
        Assert.assertNotNull("Event is null!",obj.getOrderId());
        Assert.assertTrue("Event is dirty!",!ditryObjects.contains(obj));
        Assert.assertTrue("Event is deleted!",!delObjects.contains(obj.getOrderId()));
        Assert.assertTrue("Event has been added!",!newObjects.contains(obj));
        newObjects.add(obj);
        connObjects.put(obj.getOrderId(), conn);
    }


    public void registerDel(int id) {
        Assert.assertNotNull("Event is null!",id);
        Optional<Order> obj = newObjects.stream().filter(o->o.getOrderId().equals(id)).findAny();
        if(obj.isPresent()){
            newObjects.remove(obj);
            return;
        }
        if(!ditryObjects.stream().anyMatch(o->o.getOrderId().equals(id))){
            delObjects.add(id);
        }
    }


    public void registerClean(Order obj){
        Assert.assertNotNull("Event is null!",obj.getOrderId());
    }



    public void commit() throws SQLException,RuntimeException{
        for(Order obj:newObjects){
            OrderMapper.addOrder(obj.getOrderId(), obj.getUserName(), obj.getTotalPrice(),connObjects.get(obj.getOrderId()));
        }
        for(Integer id:delObjects){
            OrderMapper.deleteOrder(id);
        }
    }

}
