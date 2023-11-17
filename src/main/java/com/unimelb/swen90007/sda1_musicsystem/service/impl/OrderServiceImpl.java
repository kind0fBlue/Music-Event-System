package com.unimelb.swen90007.sda1_musicsystem.service.impl;

import com.unimelb.swen90007.sda1_musicsystem.Mapper.OrderMapper;
import com.unimelb.swen90007.sda1_musicsystem.UoW.OrderUoW;
import com.unimelb.swen90007.sda1_musicsystem.domain.Order;
import com.unimelb.swen90007.sda1_musicsystem.service.OrderService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderMapper orderMapper = new OrderMapper();
    @Override
    public Order findOrderDescOrderId() {
        List<Order> orders = orderMapper.findOrderDescOrderId();
        return orders == null ? null  : orders.get(0);
    }

    @Override
    public Order findOrderDescOrderId(Connection conn) {
        List<Order> orders = orderMapper.findOrderDescOrderId(conn);
        return orders == null|| orders.isEmpty() ? null  : orders.get(0);
    }

    @Override
    public void addOrder(Order order, Connection conn) throws SQLException {
        OrderUoW.newCurrent();
        OrderUoW.getCurrent().registerNew(order, conn);
        OrderUoW.getCurrent().commit();
    }


    @Override
    public List<Order> findOrder(String userName,Integer orderId) {
        return orderMapper.findOrder(userName,orderId);
    }

    @Override
    public void deleteOrder(Integer orderId) throws SQLException, RuntimeException{
        OrderUoW.newCurrent();
        OrderUoW.getCurrent().registerDel(orderId);
        OrderUoW.getCurrent().commit();
    }
}
