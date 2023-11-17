package com.unimelb.swen90007.sda1_musicsystem.service;

import com.unimelb.swen90007.sda1_musicsystem.domain.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    Order findOrderDescOrderId();
    public Order findOrderDescOrderId(Connection conn);

    void addOrder(Order order, Connection conn) throws SQLException;

    List<Order> findOrder(String userName,Integer orderId);

    void deleteOrder(Integer orderId) throws SQLException, RuntimeException;
}
