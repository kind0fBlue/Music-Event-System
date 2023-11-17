package com.unimelb.swen90007.sda1_musicsystem;

import com.unimelb.swen90007.sda1_musicsystem.domain.UserRole;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/Navigate")
public class IndexServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("1111"+request.getParameter("user"));
        HttpSession session = request.getSession();
        String role=null;

        if (request.isUserInRole("Admin")) {
            role="Admin";
        } else if (request.isUserInRole("User")) {
            role="User";
        } else if (request.isUserInRole("EventPlanner")) {
            role="EventPlanner";
        }
        UserRole user = new UserRole(request.getRemoteUser(), role);
        session.setAttribute("user", user.getUsername());
        System.out.println("!!!!"+request.getRemoteUser());

        response.sendRedirect("Navigate.jsp");
        return;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }



}
