package com.unimelb.swen90007.sda1_musicsystem.Mapper;

import com.sun.security.auth.module.NTSystem;
import com.unimelb.swen90007.sda1_musicsystem.JDBCtest;
import com.unimelb.swen90007.sda1_musicsystem.LockManagerEx;
import com.unimelb.swen90007.sda1_musicsystem.domain.Event;
import com.unimelb.swen90007.sda1_musicsystem.domain.EventVenuesVo;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class EventMapper {
    public List<Event> getAssignedforPlanner(List<Integer> ids) {
        Connection connection = JDBCtest.connectRender();
        String sql = "select * from event";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Event> resultList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(5);
                if (ids.contains(id)) {
                    int venueId = resultSet.getInt(6);
                    String eventName = resultSet.getString(1);
                    String artistName = resultSet.getString(2);
                    Integer totalCapacity = resultSet.getInt(3);
                    Timestamp endTime = resultSet.getTimestamp(11);
                    Timestamp startTime = resultSet.getTimestamp(4);
                    Integer version = resultSet.getInt(16);
                    System.out.println("version number is : "+version );

                    resultList.add(new Event(id, eventName, artistName, totalCapacity, endTime, startTime, venueId, version));
                }
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

    public List<Event> getNotAssignedforPlanner(List<Integer> ids) {
        Connection connection = JDBCtest.connectRender();
        String sql = "select * from event";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Event> resultList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(5);
                if (!ids.contains(id)) {
                    int venueId = resultSet.getInt(6);
                    String eventName = resultSet.getString(1);
                    String artistName = resultSet.getString(2);
                    Integer totalCapacity = resultSet.getInt(3);
                    Timestamp endTime = resultSet.getTimestamp(11);
                    Timestamp startTime = resultSet.getTimestamp(4);
                    Integer version = resultSet.getInt(16);


                    resultList.add(new Event(id, eventName, artistName, totalCapacity, endTime, startTime, venueId,version));
                }
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

    public int getEventId(String eventName) {
        Connection connection = JDBCtest.connectRender();
        //System.out.println("eventname is " + eventName);
        String sql = "select id from event where eventname = '" + eventName + "'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int resultId = -1;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                resultId = id;
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
        return resultId;
    }

    public static Integer insertEvent(Event event) {
        Connection connection = JDBCtest.connectRender();
        String sql = "insert into event(eventname, artistname,totalcapacity,end_time, starttime, venue_id,vipprice, moshprice, standingprice, seatedprice,vipcapacity,moshcapacity,standingcapacity,seatedcapacity,version) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            //System.out.println(sql);
            statement = connection.prepareStatement(sql);
            statement.setString(1, event.getEventName());
            statement.setString(2, event.getArtistName());
            statement.setInt(3, event.getTotalCapacity());
            statement.setTimestamp(4, event.getEndTime());
            statement.setTimestamp(5, event.getStartTime());
            statement.setInt(6, event.getVenueId());
            statement.setInt(7, event.getVipPrice());
            statement.setInt(8, event.getMoshPrice());
            statement.setInt(9, event.getStandingPrice());
            statement.setInt(10, event.getSeatedPrice());
            statement.setInt(11, event.getVipCapacity());
            statement.setInt(12, event.getMoshCapacity());
            statement.setInt(13, event.getStandingCapacity());
            statement.setInt(14, event.getSeatedCapacity());
            statement.setInt(15,1);


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

    public List<Event> getList() {
        Connection connection = JDBCtest.connectRender();
        String sql = "select * from event";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Event> resultList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int venueId = resultSet.getInt(6);
                int id = resultSet.getInt(5);
                String eventName = resultSet.getString(1);
                String artistName = resultSet.getString(2);
                Integer totalCapacity = resultSet.getInt(3);
                Timestamp endTime = resultSet.getTimestamp(11);
                Timestamp startTime = resultSet.getTimestamp(4);
                Integer version = resultSet.getInt(16);


                resultList.add(new Event(id, eventName, artistName, totalCapacity, endTime, startTime, venueId,version));
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


    public List<Event> getComingList() {
        Connection connection = JDBCtest.connectRender();
        String sql = "select * from event WHERE starttime BETWEEN NOW() AND (NOW() + INTERVAL '6 months')";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Event> resultList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int venueId = resultSet.getInt(6);
                int id = resultSet.getInt(6);
                String eventName = resultSet.getString(1);
                String artistName = resultSet.getString(2);
                Integer totalCapacity = resultSet.getInt(3);
                Timestamp endTime = resultSet.getTimestamp(11);
                Timestamp startTime = resultSet.getTimestamp(4);
                Integer version = resultSet.getInt(16);
                resultList.add(new Event(id, eventName, artistName, totalCapacity, endTime, startTime, venueId,version));
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

    public static Integer deleteById(Integer id) {
        Connection connection = JDBCtest.connectRender();
        //System.out.println("the event id is " + id);
        String sql = "delete from event where id = " + id;
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

    public Integer venueHasEvent(Integer venueId) {
        Connection connection = JDBCtest.connectRender();
        //System.out.println("the venue id which has event is " + venueId);
        String sql = "update venues set has_event = ? where venue_id = " + venueId;
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setBoolean(1, true);
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

    public Integer releaseVenue(Integer venueId) {
        Connection connection = JDBCtest.connectRender();
        //System.out.println("the venue id which has event is " + venueId);
        String sql = "update venues set has_event = ? where venue_id = " + venueId;
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setBoolean(1, false);
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

    public List<EventVenuesVo> findEvent(String eventName, Integer eventId) {
        Connection connection = JDBCtest.connectRender();
        StringBuilder sql = new StringBuilder("SELECT e.eventname,e.version,e.artistname,e.totalcapacity,e.end_time,e.starttime,e.id,e.venue_id AS venueId," +
                "e.vipprice,e.moshprice,e.standingprice,e.seatedprice,e.vipcapacity,e.moshcapacity,e.standingcapacity,e.seatedcapacity," +
                "v.venuename,v.location,v.venue_id AS venueId,v.has_event As hasEvent" +
                " FROM event e " +
                " JOIN venues v ON e.venue_id = v.venue_id ");

        List<Object> parameters = new ArrayList<>();
        if (Objects.nonNull(eventName)) {
            sql.append(" WHERE e.eventname LIKE ? ");
            parameters.add("%" + eventName + "%");
        }
        if (Objects.nonNull(eventId)) {
            if (parameters.isEmpty()) {
                sql.append(" WHERE ");
            } else {
                sql.append(" AND ");
            }
            sql.append(" e.id = ? ");
            parameters.add(eventId);
        }

        //System.out.println(sql.toString());
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<EventVenuesVo> resultList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql.toString());

            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // EVENT
                String eventname = resultSet.getString("eventname");
                String artistname = resultSet.getString("artistname");
                Integer totalcapacity = resultSet.getInt("totalcapacity");
                Timestamp endTime = resultSet.getTimestamp("end_time");
                Timestamp starttime = resultSet.getTimestamp("starttime");
                Integer eId = resultSet.getInt("id");
                Integer venueId = resultSet.getInt("venueId");
                Integer vipprice = resultSet.getInt("vipprice");
                Integer moshprice = resultSet.getInt("moshprice");
                Integer standingprice = resultSet.getInt("standingprice");
                Integer seatedprice = resultSet.getInt("seatedprice");
                Integer version = resultSet.getInt("version");

                // VENUES
                String venuename = resultSet.getString("venuename");
                String location = resultSet.getString("location");
                Integer vipcapacity = resultSet.getInt("vipcapacity");
                Integer moshcapacity = resultSet.getInt("moshcapacity");
                Integer standingcapacity = resultSet.getInt("standingcapacity");
                Integer seatedcapacity = resultSet.getInt("seatedcapacity");
                Boolean hasEvent = resultSet.getBoolean("hasEvent");

                resultList.add(new EventVenuesVo(eventname, artistname, totalcapacity, endTime, starttime, eId, venueId, vipprice, moshprice, standingprice, seatedprice, venuename, location, vipcapacity, moshcapacity, standingcapacity, seatedcapacity, hasEvent, starttime,version));
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

    public List<EventVenuesVo> findEvent(String eventName, Integer eventId, Connection conn) throws RuntimeException{
        StringBuilder sql = new StringBuilder("SELECT e.eventname,e.version,e.artistname,e.totalcapacity,e.end_time,e.starttime,e.id,e.venue_id AS venueId," +
                "e.vipprice,e.moshprice,e.standingprice,e.seatedprice,e.vipcapacity,e.moshcapacity,e.standingcapacity,e.seatedcapacity," +
                "v.venuename,v.location,v.venue_id AS venueId,v.has_event As hasEvent" +
                " FROM event e " +
                " JOIN venues v ON e.venue_id = v.venue_id ");
        LockManagerEx.getInstance().acquireLock(eventId, "editEvent");
        List<Object> parameters = new ArrayList<>();
        if (Objects.nonNull(eventName)) {
            sql.append(" WHERE e.eventname LIKE ? ");
            parameters.add("%" + eventName + "%");
        }
        if (Objects.nonNull(eventId)) {
            if (parameters.isEmpty()) {
                sql.append(" WHERE ");
            } else {
                sql.append(" AND ");
            }
            sql.append(" e.id = ? ");
            parameters.add(eventId);
        }

        //System.out.println(sql.toString());
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<EventVenuesVo> resultList = new ArrayList<>();
        try {
            statement = conn.prepareStatement(sql.toString());

            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // EVENT
                String eventname = resultSet.getString("eventname");
                String artistname = resultSet.getString("artistname");
                Integer totalcapacity = resultSet.getInt("totalcapacity");
                Timestamp endTime = resultSet.getTimestamp("end_time");
                Timestamp starttime = resultSet.getTimestamp("starttime");
                Integer eId = resultSet.getInt("id");
                Integer venueId = resultSet.getInt("venueId");
                Integer vipprice = resultSet.getInt("vipprice");
                Integer moshprice = resultSet.getInt("moshprice");
                Integer standingprice = resultSet.getInt("standingprice");
                Integer seatedprice = resultSet.getInt("seatedprice");
                Integer version = resultSet.getInt("version");

                // VENUES
                String venuename = resultSet.getString("venuename");
                String location = resultSet.getString("location");
                Integer vipcapacity = resultSet.getInt("vipcapacity");
                Integer moshcapacity = resultSet.getInt("moshcapacity");
                Integer standingcapacity = resultSet.getInt("standingcapacity");
                Integer seatedcapacity = resultSet.getInt("seatedcapacity");
                Boolean hasEvent = resultSet.getBoolean("hasEvent");

                resultList.add(new EventVenuesVo(eventname, artistname, totalcapacity, endTime, starttime, eId, venueId, vipprice, moshprice, standingprice, seatedprice, venuename, location, vipcapacity, moshcapacity, standingcapacity, seatedcapacity, hasEvent, starttime,version));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
        return resultList;
    }

    public List<Event> getEventList(Integer venue_id) {
        Connection connection = JDBCtest.connectRender();
        String sql = "select * from event where venue_id='" + venue_id + "'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Event> resultList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int venueId = resultSet.getInt(6);
                int id = resultSet.getInt(5);
                String eventName = resultSet.getString(1);
                String artistName = resultSet.getString(2);
                Integer totalCapacity = resultSet.getInt(3);
                Timestamp endTime = resultSet.getTimestamp(11);
                Timestamp startTime = resultSet.getTimestamp(4);
                Integer version = resultSet.getInt(16);


                resultList.add(new Event(id, eventName, artistName, totalCapacity, endTime, startTime, venueId,version));
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


    public static class OptimisticLockException extends RuntimeException {
        public OptimisticLockException(String message) {
            super(message);
        }
    }

    public static void updateEventWithPrice(Event theEvent, Connection connection) {

        System.out.println("in the update event with price");
        ResultSet myRs = null;
        PreparedStatement myStmt = null;

        String sql = "update event set eventname = ?, artistname = ?,  end_time = ?, starttime = ?, vipprice = ?, moshprice = ?, standingprice = ?, seatedprice = ?, version = ? where id = ? and version = ?";

        try {
            myStmt = connection.prepareStatement(sql);

            myStmt.setString(1, theEvent.getEventName());
            myStmt.setString(2, theEvent.getArtistName());
            //myStmt.setInt(3, theEvent.getTotalCapacity());
            myStmt.setTimestamp(3, theEvent.getEndTime());
            myStmt.setTimestamp(4, theEvent.getStartTime());

            myStmt.setInt(5, theEvent.getVipPrice());
            myStmt.setInt(6, theEvent.getMoshPrice());
            myStmt.setInt(7, theEvent.getStandingPrice());
            myStmt.setInt(8, theEvent.getSeatedPrice());
            myStmt.setInt(9,theEvent.getVersion()+1);
            myStmt.setInt(10, theEvent.getId());
            myStmt.setInt(11,theEvent.getVersion());

            System.out.println(myStmt);
            int rowUpdated = myStmt.executeUpdate();
            if (rowUpdated==0) {
              throw new OptimisticLockException("Update failed: version number changed in the meantime.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateEventWithCapacity(Event theEvent) {

        Connection myConn = JDBCtest.connectRender();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        String sql = "update event "
                + "set vipcapacity=?, moshcapacity=?, standingcapacity=?, seatedcapacity=? "
                + "where id=?";

        try {

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, theEvent.getVipCapacity());
            myStmt.setInt(2, theEvent.getMoshCapacity());
            myStmt.setInt(3, theEvent.getStandingCapacity());
            myStmt.setInt(4, theEvent.getSeatedCapacity());

            myStmt.setInt(5, theEvent.getId());

            //System.out.println(myStmt);
            myStmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (myStmt != null) {
                    myStmt.close();
                }
                if (myStmt != null) {
                    myStmt.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void updateEventWithCapacity(Event theEvent, Connection conn) {
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        String sql = "update event "
                + "set vipcapacity=?, moshcapacity=?, standingcapacity=?, seatedcapacity=? "
                + "where id=?";

        try {

            // prepare statement
            myStmt = conn.prepareStatement(sql);

            myStmt.setInt(1, theEvent.getVipCapacity());
            myStmt.setInt(2, theEvent.getMoshCapacity());
            myStmt.setInt(3, theEvent.getStandingCapacity());
            myStmt.setInt(4, theEvent.getSeatedCapacity());

            myStmt.setInt(5, theEvent.getId());

            //System.out.println(myStmt);
            myStmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public Event getEvent(String eventID) {
        Event theEvent = null;

        Connection myConn = JDBCtest.connectRender();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        int theEventId;

        theEventId = Integer.parseInt(eventID);

        // create sql to get selected student
        String sql = "select * from event where id=?";

        try {
            // create prepared statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, theEventId);

            // execute statement
            myRs = myStmt.executeQuery();

            // retrieve data from result set row
            if (myRs.next()) {
                int venueId = myRs.getInt(6);
                int id = myRs.getInt(5);
                String eventName = myRs.getString(1);
                String artistName = myRs.getString(2);
                Integer totalCapacity = myRs.getInt(3);
                Timestamp endTime = myRs.getTimestamp(11);
                Timestamp startTime = myRs.getTimestamp(4);
                Integer version = myRs.getInt(16);

                // use the studentId during construction
                theEvent = new Event(id, eventName, artistName, totalCapacity, endTime, startTime, venueId,version);
            } else {
                System.out.println("Could not find event id: " + eventID);
            }

            return theEvent;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (myStmt != null) {
                    myStmt.close();
                }
                if (myStmt != null) {
                    myStmt.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
