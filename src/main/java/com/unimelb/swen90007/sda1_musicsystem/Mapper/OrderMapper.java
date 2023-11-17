package com.unimelb.swen90007.sda1_musicsystem.Mapper;

import com.unimelb.swen90007.sda1_musicsystem.JDBCtest;
import com.unimelb.swen90007.sda1_musicsystem.LockManagerEx;
import com.unimelb.swen90007.sda1_musicsystem.domain.Order;
import com.unimelb.swen90007.sda1_musicsystem.domain.Ticket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderMapper {
    private TicketMapper ticketMapper = new TicketMapper();

    public List<Order> findOrderDescOrderId() {
        Connection connection = JDBCtest.connectRender();
        String sql = "select order_id AS orderId, date,user_name AS userName,total_price AS totalPrice,date from " + "\"" + "order" + "\"" + "where 1=1 order by order_id desc";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Order> resultList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer orderId = resultSet.getInt("orderId");
                String userName = resultSet.getString("userName");
                Integer totalPrice = resultSet.getInt("totalPrice");
                String date = resultSet.getString("date");
                resultList.add(new Order(orderId, userName, totalPrice, date, null));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return resultList;
    }


    public List<Order> findOrderDescOrderId(Connection conn) {
        String sql = "select order_id AS orderId, date,user_name AS userName,total_price AS totalPrice,date from " + "\"" + "order" + "\"" + "where 1=1 order by order_id desc";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Order> resultList = new ArrayList<>();
        try {
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer orderId = resultSet.getInt("orderId");
                String userName = resultSet.getString("userName");
                Integer totalPrice = resultSet.getInt("totalPrice");
                String date = resultSet.getString("date");
                resultList.add(new Order(orderId, userName, totalPrice, date, null));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
        return resultList;
    }

    public static void addOrder(int orderId, String userName, Integer totalAmoumt, Connection conn) throws SQLException {
        String sql = "insert into" + "\"" + "order" + "\"" + "(order_id, date,user_name,total_price) values(?,?,?,?)";
        PreparedStatement statement = null;
        Integer result = null;
            statement = conn.prepareStatement(sql);
            statement.setInt(1, orderId);
            statement.setDate(2, new Date(System.currentTimeMillis()));
            statement.setString(3, userName);
            statement.setInt(4, totalAmoumt);
            statement.executeUpdate();
    }

    public List<Order> findOrder(String userName, Integer orderId) {
        Connection connection = JDBCtest.connectRender();
        String sql = "select order_id AS orderId, date,user_name AS userName,total_price AS totalPrice  from" + "\"" + "order" + "\"";
        if (Objects.nonNull(userName)) {
            sql += "WHERE user_name ='" + userName + "'";
        }
        if (Objects.nonNull(orderId)) {
            sql += "WHERE order_id =" + orderId;
        }
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Order> resultList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderId = resultSet.getInt("orderId");
                userName = resultSet.getString("userName");
                Integer totalPrice = resultSet.getInt("totalPrice");
                String date = resultSet.getString("date");
//                List<Ticket> tickets = this.ticketMapper.findTicketByOrderId(orderId);
                resultList.add(new Order(orderId, userName, totalPrice, date, null));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return resultList;
    }

    public static void deleteOrder(Integer orderId) throws RuntimeException{

        Connection connection = JDBCtest.connectRender();
        LockManagerEx.getInstance().acquireOrderLock(orderId, "deleteOrder");
        String sql = "delete from " + "\"" + "order" + "\"" + " where order_id = " + orderId;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            LockManagerEx.getInstance().releaseOrderLck(orderId,"deleteOrder");
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Order> findOrderByTicket(Integer orderId) {
        Connection connection = JDBCtest.connectRender();
        String sql = "select order_id AS orderId, date,user_name AS userName,total_price AS totalPrice  from" + "\"" + "order" + "\"";
        if (Objects.nonNull(orderId)) {
            sql += "WHERE order_id =" + orderId;
        }
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Order> resultList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderId = resultSet.getInt("orderId");
                String userName = resultSet.getString("userName");
                Integer totalPrice = resultSet.getInt("totalPrice");
                String date = resultSet.getString("date");
                List<Ticket> tickets = this.ticketMapper.findTicketByOrderId(orderId);
                resultList.add(new Order(orderId, userName, totalPrice, date, tickets));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return resultList;
    }

}
