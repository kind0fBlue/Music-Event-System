package com.unimelb.swen90007.sda1_musicsystem.Mapper;

import com.unimelb.swen90007.sda1_musicsystem.JDBCtest;
import com.unimelb.swen90007.sda1_musicsystem.domain.Event;
import com.unimelb.swen90007.sda1_musicsystem.domain.EventAssigend;
import com.unimelb.swen90007.sda1_musicsystem.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventAssignedMapper {

    public static EventAssigend getAssignedList(String username) {
        Connection connection = JDBCtest.connectRender();
        String sql = "select * from eventassign where username = '"+username+"'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        EventAssigend result = null;
        String userName = null;
        List<Integer> resultList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userName = resultSet.getString(1);
                resultList.add(resultSet.getInt(2));
            }
            EventMapper events =new EventMapper();
            result=new EventAssigend(userName, events.getAssignedforPlanner(resultList));
        }
        catch (SQLException e) {
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
        return result;
    }


        public EventAssigend gettNotAssignedList(String username) {
            Connection connection = JDBCtest.connectRender();
            String sql = "select * from eventassign where username = '"+username+"'";
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            EventAssigend result = null;
            String userName = null;
            List<Integer> resultList = new ArrayList<>();
            try {
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    userName = resultSet.getString(1);
                    resultList.add(resultSet.getInt(2));
                }
                EventMapper events =new EventMapper();
                result=new EventAssigend(userName, events.getNotAssignedforPlanner(resultList));
            }
            catch (SQLException e) {
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
            return result;
        }

    public static Integer insertUser(EventAssigend event) {
        Connection connection = JDBCtest.connectRender();
        String sql = "insert into eventassign(username, eventid) values(?,?)";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, event.getUsername());
            statement.setInt(2, event.getEventId());
            result = statement.executeUpdate();
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
        return result;
    }

    public static List<Event> getAssigned(String username) {
        Connection connection = JDBCtest.connectRender();
        String sql = "select * from eventassign where username = '"+username+"'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Integer> resultList = new ArrayList<>();
        List<Event> result = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resultList.add(resultSet.getInt(2));
            }
            EventMapper events =new EventMapper();
            result= events.getAssignedforPlanner(resultList);
        }
        catch (SQLException e) {
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
        return result;
    }

    public static Integer deleteEventAssignedById(Integer id) {
        Connection connection = JDBCtest.connectRender();
        System.out.println("the event id is " + id);
        String sql = "delete from eventAssign where eventid = " + id;
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeUpdate();
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
        return result;
    }
}
