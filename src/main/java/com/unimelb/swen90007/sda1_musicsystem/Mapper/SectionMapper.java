package com.unimelb.swen90007.sda1_musicsystem.Mapper;

import com.unimelb.swen90007.sda1_musicsystem.JDBCtest;
import com.unimelb.swen90007.sda1_musicsystem.domain.VenueSection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SectionMapper{

    public Integer insertSection(VenueSection venueSection) {
        Connection connection = JDBCtest.connectRender();
        String sql = "insert into section(type ,capacity, venue_id) values(?,?,?)";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, venueSection.getType());
            statement.setInt(2, venueSection.getCapacity());
            statement.setInt(3, venueSection.getVenueId());

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
}
