package com.example.mapper.shiyanlou01;

import com.example.entity.Teacher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TeacherMapper {

     List<Teacher> findAll();
}
