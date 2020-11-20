package com.example.controller;

import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.mapper.shiyanlou01.TeacherMapper;
import com.example.mapper.shiyanlou02.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@RestController
public class TestController {


    //从容器中获取teacherMapper
    @Autowired
    private TeacherMapper teacherMapper;

    //从容器中获取studentMapper
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 从shiyanlou01中查询所有教师信息
     */
    @GetMapping("/teacher")
    public List<Teacher> findAllTeacher() {
        List<Teacher> teachers = teacherMapper.findAll();
        return teachers;
    }

    /**
     * 从shiyanlou02中查询所有学生信息
     */
    @GetMapping("/student")
    public List<Student> findAllStudent() {
        List<Student> students = studentMapper.findAll();
        return students;
    }

}
