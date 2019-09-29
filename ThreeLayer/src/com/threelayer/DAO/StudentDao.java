package com.threelayer.DAO;

import com.threelayer.entity.Student;
import com.threelayer.util.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    /**
     * 判断记录是否存在
     *
     * @param sno
     * @return
     */
    public boolean isExists(int sno) throws SQLException {
        String sql = "select * from Student where sno = ?";
        Object[] parms = {sno};
        ResultSet resultSet = DBUtils.executeQuery(sql, parms);
        if (resultSet.next()) {
            DBUtils.closeAll(DBUtils.connection, DBUtils.pstm, resultSet);
            return true;
        } else
            return false;

    }

    public boolean addStudent(Student student) {
        String addsql = "insert into student (sno,sname,sage,saddress) values (?,?,?,?)";
        Object[] parms = {student.getSno(), student.getSname(), student.getSage(), student.getSaddress()};
        return DBUtils.executeUpdate(addsql, parms);
    }

    public boolean deleteStudent(int sno) {
        String deletesql = "delete from student where sno = ?";
        Object[] parms = {sno};
        return DBUtils.executeUpdate(deletesql, parms);
    }

    public boolean updateStudent(int sno, Student student) {
        String altersql = "update student set sname = ?,sage = ?,saddress = ? where sno = ?";
        Object[] parms = {student.getSname(), student.getSage(), student.getSaddress(), sno};
        return DBUtils.executeUpdate(altersql, parms);
    }
    //查找所有学生信息
    public List<Student> queryAllStudent() throws SQLException {
        String sql = "select * from student";
        ResultSet rst = DBUtils.executeQuery(sql, null);
        List<Student> studentList = new ArrayList<>();
        while (rst.next()) {
            int sno = rst.getInt(1);
            String sname = rst.getString(2);
            int sage = rst.getInt(3);
            String saddress = rst.getString(4);
            Student student = new Student(sno, sname, sage, saddress);
            studentList.add(student);
        }
        return studentList;
    }
    //查找单个学生信息
    public Student queryStudentBySno(int sno) throws SQLException {
        String sql= "select * from student where sno = ?";
        Object[] parms = {sno};
        ResultSet rst = DBUtils.executeQuery(sql, parms);
        Student student = new Student() ;
        if (rst.next()){
            String  sname = rst.getString(2);
            int sage = rst.getInt(3);
            String saddress = rst.getString(4);
            student.setSno(sno);
            student.setSname(sname);
            student.setSage(sage);
            student.setSaddress(saddress);
        }
        return student;

    }


}
