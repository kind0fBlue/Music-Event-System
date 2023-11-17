package com.unimelb.swen90007.sda1_musicsystem.Mapper;

import com.unimelb.swen90007.sda1_musicsystem.JDBCtest;
import com.unimelb.swen90007.sda1_musicsystem.domain.Venue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VenueMapper {

    public static Integer insertVenue(Venue venue) {
        Connection connection = JDBCtest.connectRender();
        String sql = "insert into venues(venuename, location) values(?,?)";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, venue.getVenueName());
            statement.setString(2, venue.getLocation());
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

    public List<Venue> availableVenues() {
        Connection connection = JDBCtest.connectRender();
        String sql = "select * from venues";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Venue> resultList = new ArrayList<>();
        System.out.println("result set is" + resultSet);
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("into the loop");
                String venueName = resultSet.getString(1);
                String location = resultSet.getString(2);
                Integer id = resultSet.getInt(7);
                System.out.println("Id is " + id);
                resultList.add(new Venue(id, venueName, location));
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

    public static void updateVenue(Integer venueId, int vipcapacity, int moshcapacity, int standingcapacity, int seatedcapacity) {
        Connection connection = JDBCtest.connectRender();
        String sql = "update venues set vipcapacity=?,moshcapacity=?,standingcapacity=?, seatedcapacity=? where venue_id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, vipcapacity);
            statement.setInt(2, moshcapacity);
            statement.setInt(3, standingcapacity);
            statement.setInt(4, seatedcapacity);
            statement.setInt(5, venueId);
            statement.executeUpdate();
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
    }

}