package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/index")
    public String index(Model model) {

        ArrayList<User> users = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            User u = new User();
            u.setId(i);
            u.setName("shiyanlou:" + i);
            u.setAge(i + 10);
            users.add(u);
        }

        model.addAttribute("course", "Spring boot 整合 Thymeleaf");
        model.addAttribute("users", users);
        return "index";
    }


}
