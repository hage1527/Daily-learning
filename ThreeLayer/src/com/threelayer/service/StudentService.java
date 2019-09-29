package com.threelayer.service;

import com.threelayer.DAO.StudentDao;
import com.threelayer.entity.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    StudentDao studentDao = new StudentDao();
    //查询所有学生信息
    public List<Student> queryAllStudentService() throws SQLException {
        return studentDao.queryAllStudent();
    }
    //根据学号查询单个学生信息
    public Student queryStudentInfoBySnoService(int sno) throws SQLException {

        return studentDao.queryStudentBySno(sno);
    }
    //根据学号改变(修改)单个学生信息
    public boolean updateStudentService(int sno,Student student){
        return  studentDao.updateStudent(sno, student);
    }
    //根据学号删除单个学生信息
    public boolean deleteStudentService(int sno){
        return studentDao.deleteStudent(sno);
    }
    //增加一条学生信息
    public boolean addStudentService(Student student){
        return studentDao.addStudent(student);
    }



}
