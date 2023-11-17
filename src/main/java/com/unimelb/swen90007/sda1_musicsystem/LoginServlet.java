package com.unimelb.swen90007.sda1_musicsystem;

import com.unimelb.swen90007.sda1_musicsystem.domain.UserRole;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet("/")
@ServletSecurity(value = @HttpConstraint(rolesAllowed = { "User", "Admin", "EventPlanner" }))

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (request.isUserInRole("Admin")) {
            UserRole user = new UserRole(request.getRemoteUser(), "Admin");
            session.setAttribute("user", user);

            // Get username
            // UserRole test= (UserRole) session.getAttribute("user");
            // System.out.println(test.getUsername());

            response.sendRedirect("Navigate.jsp");
            return;
        } else if (request.isUserInRole("User")) {
            UserRole user = new UserRole(request.getRemoteUser(), "User");
            session.setAttribute("user", user);
            response.sendRedirect("Navigate.jsp");
            return;
        } else if (request.isUserInRole("EventPlanner")) {
            UserRole user = new UserRole(request.getRemoteUser(), "EventPlanner");
            request.setAttribute("user", user.getUsername());
            request.getRequestDispatcher("Navigate.jsp").forward(request,response);
            return;
        } else {
            response.sendRedirect("error.jsp");
            return;
        }

        // PrintWriter writer = response.getWriter();
        // writer.println("<h3> role: success </h3>"
        // );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
