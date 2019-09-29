package com.hage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletTest",urlPatterns = "/ServletTest")
public class ServletTest extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("init.....");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doget.....");
    }

    @Override
    public void destroy() {
        System.out.println("destroy.....");
    }
}
