package com.unimelb.swen90007.sda1_musicsystem;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import org.testng.annotations.Test;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;




public class EventPlannerServletTest {

    final CountDownLatch latch = new CountDownLatch(1);
    EventPlannerServlet servlet = new EventPlannerServlet();

    @Test
    public void addEventConcurrencyTest() {
        Runnable addEventTask = () -> {
            try {
                // Mocking HttpServletRequest and HttpServletResponse
                HttpServletRequest mockRequest = mock(HttpServletRequest.class);
                HttpServletResponse mockResponse = mock(HttpServletResponse.class);


                HttpSession mockSession = mock(HttpSession.class);
                RequestDispatcher mockDispatcher = mock(RequestDispatcher.class);

                // ...

                // Define return value for getSession(true) on mockRequest
                when(mockRequest.getSession(true)).thenReturn(mockSession);

                // Optionally define return values for methods called on mockSession
                when(mockSession.getId()).thenReturn("someSessionId");


                when(mockRequest.getRequestDispatcher(anyString())).thenReturn(mockDispatcher);


/*
            user = req.getParameter("user");
            String eventName = req.getParameter("eventName");
            String artist = req.getParameter("artistName");
            String starttime = req.getParameter("startTime");
            String endtime = req.getParameter("endTime");
            int vipPrice = Integer.parseInt(req.getParameter("vipPrice"));
            int moshPrice  = Integer.parseInt(req.getParameter("moshPrice"));
            int standingPrice = Integer.parseInt(req.getParameter("standingPrice"));
            int seatedPrice = Integer.parseInt(req.getParameter("seatedPrice"));

            int vipCapacity = Integer.parseInt(req.getParameter("vipCapacity"));
            int moshCapacity  = Integer.parseInt(req.getParameter("moshCapacity"));
            int standingCapacity = Integer.parseInt(req.getParameter("standingCapacity"));
            int seatedCapacity = Integer.parseInt(req.getParameter("seatedCapacity"));
            int totalCapacity = vipCapacity+moshCapacity+standingCapacity+seatedCapacity;


            Timestamp startTime = Timestamp.valueOf(starttime);
            Timestamp endTime = Timestamp.valueOf(endtime);
            int venueId = Integer.parseInt(req.getParameter("venueId"));

 */

                // Defining request parameters
                when(mockRequest.getParameter("method")).thenReturn("add");
                when(mockRequest.getParameter("user")).thenReturn("eventplanner");

                when(mockRequest.getParameter("venueId")).thenReturn("6");
                when(mockRequest.getParameter("eventName")).thenReturn("IsleWIght4");
                when(mockRequest.getParameter("artistName")).thenReturn("jimi");
                when(mockRequest.getParameter("startTime")).thenReturn("2023-10-20 11:00:00");
                when(mockRequest.getParameter("endTime")).thenReturn("2023-10-20 13:00:00");
                when(mockRequest.getParameter("vipPrice")).thenReturn("100");
                when(mockRequest.getParameter("moshPrice")).thenReturn("100");
                when(mockRequest.getParameter("standingPrice")).thenReturn("100");
                when(mockRequest.getParameter("seatedPrice")).thenReturn("100");
                when(mockRequest.getParameter("vipCapacity")).thenReturn("100");
                when(mockRequest.getParameter("moshCapacity")).thenReturn("100");
                when(mockRequest.getParameter("standingCapacity")).thenReturn("100");
                when(mockRequest.getParameter("seatedCapacity")).thenReturn("100");



                // Call the servlet's doPost method to simulate a post request
                servlet.doPost(mockRequest, mockResponse);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (jakarta.servlet.ServletException e) {
                throw new RuntimeException(e);
            }


        };



        Runnable addEventTask2 = () -> {
            try {
                // Mocking HttpServletRequest and HttpServletResponse
                HttpServletRequest mockRequest = mock(HttpServletRequest.class);
                HttpServletResponse mockResponse = mock(HttpServletResponse.class);


                HttpSession mockSession = mock(HttpSession.class);
                RequestDispatcher mockDispatcher = mock(RequestDispatcher.class);

                // ...

                // Define return value for getSession(true) on mockRequest
                when(mockRequest.getSession(true)).thenReturn(mockSession);

                // Optionally define return values for methods called on mockSession
                when(mockSession.getId()).thenReturn("someSessionId");


                when(mockRequest.getRequestDispatcher(anyString())).thenReturn(mockDispatcher);


/*
            user = req.getParameter("user");
            String eventName = req.getParameter("eventName");
            String artist = req.getParameter("artistName");
            String starttime = req.getParameter("startTime");
            String endtime = req.getParameter("endTime");
            int vipPrice = Integer.parseInt(req.getParameter("vipPrice"));
            int moshPrice  = Integer.parseInt(req.getParameter("moshPrice"));
            int standingPrice = Integer.parseInt(req.getParameter("standingPrice"));
            int seatedPrice = Integer.parseInt(req.getParameter("seatedPrice"));

            int vipCapacity = Integer.parseInt(req.getParameter("vipCapacity"));
            int moshCapacity  = Integer.parseInt(req.getParameter("moshCapacity"));
            int standingCapacity = Integer.parseInt(req.getParameter("standingCapacity"));
            int seatedCapacity = Integer.parseInt(req.getParameter("seatedCapacity"));
            int totalCapacity = vipCapacity+moshCapacity+standingCapacity+seatedCapacity;


            Timestamp startTime = Timestamp.valueOf(starttime);
            Timestamp endTime = Timestamp.valueOf(endtime);
            int venueId = Integer.parseInt(req.getParameter("venueId"));

 */

                // Defining request parameters
                when(mockRequest.getParameter("method")).thenReturn("add");
                when(mockRequest.getParameter("user")).thenReturn("eventplanner");

                when(mockRequest.getParameter("venueId")).thenReturn("6");
                when(mockRequest.getParameter("eventName")).thenReturn("IsleWIght5");
                when(mockRequest.getParameter("artistName")).thenReturn("jimi");
                when(mockRequest.getParameter("startTime")).thenReturn("2023-10-20 11:00:00");
                when(mockRequest.getParameter("endTime")).thenReturn("2023-10-20 13:00:00");
                when(mockRequest.getParameter("vipPrice")).thenReturn("100");
                when(mockRequest.getParameter("moshPrice")).thenReturn("100");
                when(mockRequest.getParameter("standingPrice")).thenReturn("100");
                when(mockRequest.getParameter("seatedPrice")).thenReturn("100");
                when(mockRequest.getParameter("vipCapacity")).thenReturn("100");
                when(mockRequest.getParameter("moshCapacity")).thenReturn("100");
                when(mockRequest.getParameter("standingCapacity")).thenReturn("100");
                when(mockRequest.getParameter("seatedCapacity")).thenReturn("100");



                // Call the servlet's doPost method to simulate a post request
                servlet.doPost(mockRequest, mockResponse);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (jakarta.servlet.ServletException e) {
                throw new RuntimeException(e);
            }


        };


        int numberOfEventsToAdd = 10;  // Arbitrary number of events to add
        Thread[] threads = new Thread[numberOfEventsToAdd];


        // Start multiple threads to simulate concurrent event adding
        for (int i = 0; i < numberOfEventsToAdd; i++) {
            if ((i % 2) == 0) {
                threads[i] = new Thread(addEventTask);
                threads[i].start();
            }
            else {
                threads[i] = new Thread(addEventTask2);
                threads[i].start();
            }
        }

        // Wait for all threads to finish
        for (int i = 0; i < numberOfEventsToAdd; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }








}
