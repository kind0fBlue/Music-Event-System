package com.unimelb.swen90007.sda1_musicsystem;


import org.testng.annotations.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class updateAddEventTest {


    @Test
    public void testConcurrencyAddUpdate() throws InterruptedException {
        final String TEST_EVENT_ID = "96"; // Assuming the event with ID 1 exists

        final CountDownLatch latch = new CountDownLatch(1);
        EventPlannerServlet servlet = new EventPlannerServlet();




        Runnable updateEventTask = () -> {
            try {
                HttpServletRequest mockRequest = mock(HttpServletRequest.class);
                HttpServletResponse mockResponse = mock(HttpServletResponse.class);


                HttpSession mockSession = mock(HttpSession.class);
                RequestDispatcher mockDispatcher = mock(RequestDispatcher.class);

                // Define return value for getSession(true) on mockRequest
                when(mockRequest.getSession(true)).thenReturn(mockSession);

                // Optionally define return values for methods called on mockSession
                when(mockSession.getId()).thenReturn("someSessionId");


                when(mockRequest.getRequestDispatcher(anyString())).thenReturn(mockDispatcher);



        /*


        int update_id = Integer.parseInt(req.getParameter("eventId"));
                String update_eventName = req.getParameter("eventName");
                String update_artistName = req.getParameter("artistName");
                //int update_totalCapacity = Integer.parseInt(req.getParameter("totalCapacity"));
                Timestamp update_startTime = Timestamp.valueOf(req.getParameter("startTime"));
                Timestamp update_endTime = Timestamp.valueOf(req.getParameter("endTime"));
                int vip = Integer.parseInt(req.getParameter("vipPrice"));
                int mosh  = Integer.parseInt(req.getParameter("moshPrice"));
                int standing = Integer.parseInt(req.getParameter("standingPrice"));
                int seated = Integer.parseInt(req.getParameter("seatedPrice"));
                int version = Integer.parseInt(req.getParameter("version"));

                int update_venueId = Integer.parseInt(req.getParameter("venue"));


         */


                when(mockRequest.getParameter("method")).thenReturn("UpdateEvent");
                when(mockRequest.getParameter("user")).thenReturn("eventplanner");

                when(mockRequest.getParameter("version")).thenReturn("1");


                when(mockRequest.getParameter("eventId")).thenReturn(TEST_EVENT_ID);
                when(mockRequest.getParameter("venue")).thenReturn("1001");
                when(mockRequest.getParameter("eventName")).thenReturn("testingEvent100111");
                when(mockRequest.getParameter("artistName")).thenReturn("jimi");
                when(mockRequest.getParameter("startTime")).thenReturn("2023-10-20 06:00:00");
                when(mockRequest.getParameter("endTime")).thenReturn("2023-10-20 07:00:00");
                when(mockRequest.getParameter("vipPrice")).thenReturn("100");
                when(mockRequest.getParameter("moshPrice")).thenReturn("100");
                when(mockRequest.getParameter("standingPrice")).thenReturn("100");
                when(mockRequest.getParameter("seatedPrice")).thenReturn("100");
                when(mockRequest.getParameter("totalCapacity")).thenReturn("400");
                latch.await();





                // Call the servlet's doPost method to simulate a post request
                servlet.doPost(mockRequest, mockResponse);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (jakarta.servlet.ServletException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        };


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

                when(mockRequest.getParameter("venueId")).thenReturn("1001");
                when(mockRequest.getParameter("eventName")).thenReturn("testingEventNew4.0");
                when(mockRequest.getParameter("artistName")).thenReturn("jimi");
                when(mockRequest.getParameter("startTime")).thenReturn("2023-10-20 06:00:00");
                when(mockRequest.getParameter("endTime")).thenReturn("2023-10-20 07:00:00");
                when(mockRequest.getParameter("vipPrice")).thenReturn("100");
                when(mockRequest.getParameter("moshPrice")).thenReturn("100");
                when(mockRequest.getParameter("standingPrice")).thenReturn("100");
                when(mockRequest.getParameter("seatedPrice")).thenReturn("100");
                when(mockRequest.getParameter("vipCapacity")).thenReturn("100");
                when(mockRequest.getParameter("moshCapacity")).thenReturn("100");
                when(mockRequest.getParameter("standingCapacity")).thenReturn("100");
                when(mockRequest.getParameter("seatedCapacity")).thenReturn("100");

                latch.await();




                // Call the servlet's doPost method to simulate a post request
                servlet.doPost(mockRequest, mockResponse);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (jakarta.servlet.ServletException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        };






        Thread thread1 = new Thread(addEventTask);
        Thread thread2 = new Thread(updateEventTask);

        thread1.start();
        thread2.start();

        latch.countDown(); // This will release both threads at the same time

        thread1.join();
        thread2.join();

        // Assertions to check if optimistic locking worked as expected
        // Possibly check the number of update operations that were successful, or check the state of the database
        // assertEquals(expectedState, actualState);
    }
}
