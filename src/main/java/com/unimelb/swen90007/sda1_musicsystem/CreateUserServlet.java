package com.unimelb.swen90007.sda1_musicsystem;

import com.unimelb.swen90007.sda1_musicsystem.UoW.UserUoW;
import com.unimelb.swen90007.sda1_musicsystem.domain.UserWithRole;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CreateUser", value = "/CreateUser")

public class CreateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("createuser.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        UserUoW.newCurrent();
        UserWithRole userNew=new UserWithRole(userName,password,role);
        UserUoW.getCurrent().commit();
        resp.sendRedirect("viewuser?method=display");
    }

}
