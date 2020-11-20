package com.example.mapper.shiyanlou02;

import com.example.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentMapper {

    List<Student> findAll();

}
