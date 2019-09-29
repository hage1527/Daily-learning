package com.threelayer.view;

import com.threelayer.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteStudentServlet",urlPatterns = "/deleteStudentServlet")
public class deleteStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        StudentService service = new StudentService();
        int sno =Integer.parseInt(request.getParameter("sno"));
        boolean result = service.deleteStudentService(sno);
        if (result) {
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
