package com.hage.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletLogin",urlPatterns = "/a")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post");
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        Login login = new Login(uname, upwd);
        int result = LoginDAO.login(login);
        if (result>0) {
            //request.getRequestDispatcher("welcome.jsp").forward(request, response);
            response.sendRedirect("welcome.jsp");
        }
        else{
            response.sendRedirect("index.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
