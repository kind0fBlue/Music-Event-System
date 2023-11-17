package com.unimelb.swen90007.sda1_musicsystem;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TestTriggerAddUpdateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Optionally: Add Authentication and Authorization Checks Here

        // Initialize your test class and run the test method
        updateAddEventTest test = new updateAddEventTest();
        try {
            test.testConcurrencyAddUpdate();
            resp.getWriter().write("Test executed successfully.");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Test execution failed: " + e.getMessage());
        }
    }
}