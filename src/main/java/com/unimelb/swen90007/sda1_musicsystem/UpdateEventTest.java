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


public class UpdateEventTest {

    @Test
    public void testConcurrencyHandling() throws InterruptedException {
        final String TEST_EVENT_ID = "91"; // Assuming the event with ID 1 exists

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

                when(mockRequest.getParameter("version")).thenReturn("8");


                when(mockRequest.getParameter("eventId")).thenReturn(TEST_EVENT_ID);
                when(mockRequest.getParameter("venue")).thenReturn("1001");
                when(mockRequest.getParameter("eventName")).thenReturn("IsleWaIghtNewNew");
                when(mockRequest.getParameter("artistName")).thenReturn("jimi");
                when(mockRequest.getParameter("startTime")).thenReturn("2023-10-20 09:00:00");
                when(mockRequest.getParameter("endTime")).thenReturn("2023-10-20 10:00:00");
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






        Thread thread1 = new Thread(updateEventTask);
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
