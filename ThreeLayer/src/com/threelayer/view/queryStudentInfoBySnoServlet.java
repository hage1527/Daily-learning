package com.threelayer.view;

import com.threelayer.entity.Student;
import com.threelayer.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/queryStudentInfoBySnoServlet")
public class queryStudentInfoBySnoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("utf-8");
         int sno=Integer.parseInt(request.getParameter("sno"));
        StudentService service = new StudentService();
        try {
            Student student = service.queryStudentInfoBySnoService(sno);
            request.setAttribute("student", student);
            request.getRequestDispatcher("studentInfo.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request, response);
    }
}
