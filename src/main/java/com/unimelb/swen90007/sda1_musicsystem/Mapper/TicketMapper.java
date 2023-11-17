package com.unimelb.swen90007.sda1_musicsystem.Mapper;

import com.unimelb.swen90007.sda1_musicsystem.JDBCtest;
import com.unimelb.swen90007.sda1_musicsystem.domain.Order;
import com.unimelb.swen90007.sda1_musicsystem.domain.Ticket;
import com.unimelb.swen90007.sda1_musicsystem.domain.Price;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TicketMapper {
    public List<Ticket> findTicketByOrderId(Integer orderId) {
        Connection connection = JDBCtest.connectRender();
        String sql = "select order_id AS orderId, price_amount As priceAmount, price_currency AS priceCurrency, section ,ticket_id AS ticketId ,event_id AS eventId from ticket ";
        if (Objects.nonNull(orderId)) {
            sql += "WHERE order_id =" + orderId;
        }
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Ticket> resultList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderId = resultSet.getInt("orderId");
                Integer ticketId = resultSet.getInt("ticketId");
                Integer eventId = resultSet.getInt("eventId");
                String section = resultSet.getString("section");
                Integer priceAmount = resultSet.getInt("priceAmount");
                String priceCurrency = resultSet.getString("priceCurrency");

                Price price = new Price(priceAmount, priceCurrency);
                resultList.add(new Ticket(price, section, ticketId, eventId, orderId));

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

    public static void deleteTicket(Integer orderId) {
        Connection connection = JDBCtest.connectRender();
        String sql = "delete from " + "\"" + "ticket" + "\"" + " where order_id = " + orderId;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
    }


    public List<Ticket> findTicketByEventId(Integer eventId) {
        Connection connection = JDBCtest.connectRender();
        String sql = "select event_id AS eventId, price_amount As priceAmount, price_currency AS priceCurrency, section, ticket_id AS ticketId, order_id AS orderId from ticket ";
        if (Objects.nonNull(eventId)) {
            sql += "WHERE event_id =" + eventId;
        }
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Ticket> resultList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer orderId = resultSet.getInt("orderId");
                Integer ticketId = resultSet.getInt("ticketId");
                Integer id = resultSet.getInt("eventId");
                String section = resultSet.getString("section");
                Integer priceAmount = resultSet.getInt("priceAmount");
                String priceCurrency = resultSet.getString("priceCurrency");

                /***********Embedded value************/
                Price priceObj = new Price(priceAmount, priceCurrency);
                resultList.add(new Ticket(priceObj, section, ticketId, eventId, orderId));
                /***********Embedded value************/

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

    public static void addTicket(Price priceObj, String section, Integer eventId, int orderId,Connection conn) {
        String sql = "insert into ticket(price_amount, price_currency, section, event_id, order_id) values(?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            /***********Embedded value************/
            statement.setInt(1, priceObj.getAmount());
            statement.setString(2, priceObj.getCurrency());
            /***********Embedded value************/
            statement.setString(3, section);
            statement.setInt(4, eventId);
            statement.setInt(5, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
