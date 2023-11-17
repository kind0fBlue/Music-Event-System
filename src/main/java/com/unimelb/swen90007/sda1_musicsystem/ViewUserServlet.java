package com.unimelb.swen90007.sda1_musicsystem;

import com.unimelb.swen90007.sda1_musicsystem.Mapper.UserWithRoleMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ViewUser", value = "/viewuser")
public class ViewUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("display")) {
            UserWithRoleMapper user=new UserWithRoleMapper();
            req.setAttribute("userList", user.getList());
            req.getRequestDispatcher("viewuser.jsp").forward(req, resp);
        }
    }

}
