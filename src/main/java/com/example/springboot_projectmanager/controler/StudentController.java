package com.example.springboot_projectmanager.controler;

import com.example.springboot_projectmanager.entity.Student;
import com.example.springboot_projectmanager.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/{id}/home")
    public String showHomePage(@PathVariable int id, Model model) {

        Student student = studentService.findById(id);

        model.addAttribute("student", student);

        return "home-page";
    }

}
