package com.threelayer.view;

import com.threelayer.entity.Student;
import com.threelayer.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addStudentServlet", urlPatterns = "/addStudentServlet")
public class addStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //封装request数据
        int sno = Integer.parseInt(request.getParameter("sno"));
        String sname = request.getParameter("sname");
        int sage = Integer.parseInt(request.getParameter("sage"));
        String saddress = request.getParameter("saddress");
        //service
        StudentService service = new StudentService();
        Student student = new Student(sno, sname, sage, saddress);
        boolean result = service.addStudentService(student);
        if (result) {
            System.out.println("增加成功");
        } else {
            System.out.println("增加失败");
        }

    }
}
