package com.example.springboot_projectmanager.controler;


import com.example.springboot_projectmanager.entity.Student;
import com.example.springboot_projectmanager.service.impl.StudentServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private StudentServiceImpl studentService;
    @GetMapping("/")
    public String registration(Model model) {

        model.addAttribute("student", new Student());

        return "registration-page";
    }

    @PostMapping("/registerStudent")
    public String registerStudent(@ModelAttribute("student") Student student, HttpSession session) {

        studentService.saveStudent(student);

        Student loginStud = studentService.findByEmail(student.getEmail());
        session.setAttribute("loggedInUser", loginStud);

        return "redirect:/student/" + student.getId() + "/home";
    }

}
