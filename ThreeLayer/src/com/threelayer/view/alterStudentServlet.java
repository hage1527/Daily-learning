package com.threelayer.view;

import com.threelayer.entity.Student;
import com.threelayer.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "alterStudentServlet",urlPatterns = "/alterStudentServlet")
public class alterStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //调用service
        StudentService service = new StudentService();
        //封装jsp发过来的数据
        int sno = Integer.parseInt(request.getParameter("sno"));
        String sname = request.getParameter("sname");
        int sage = Integer.parseInt(request.getParameter("sage"));
        String saddress = request.getParameter("saddress");
        Student student = new Student(sno, sname, sage, saddress);
        //
        boolean result = service.updateStudentService(sno, student);
        if (result){
            System.out.println("修改成功");
        }else
            System.out.println("修改失败");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
