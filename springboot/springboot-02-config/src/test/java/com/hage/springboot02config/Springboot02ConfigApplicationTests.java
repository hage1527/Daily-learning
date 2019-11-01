package com.hage.springboot02config;

import com.hage.springboot02config.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Springboot02ConfigApplicationTests {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void contextLoads() {
    }

    @Test
    void getBean(){
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
    }
    @Test
    void contains(){
        boolean service = applicationContext.containsBean("createService");
        System.out.println(service);
    }

}
