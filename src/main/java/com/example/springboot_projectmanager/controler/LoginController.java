package com.example.springboot_projectmanager.controler;

import com.example.springboot_projectmanager.entity.Student;
import com.example.springboot_projectmanager.service.impl.StudentServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/")
    public String login(Model model) {

        model.addAttribute("student", new Student());

        return "login-page";
    }

    @PostMapping("/loginStudent")
    public String loginStudent(@ModelAttribute("student") Student student, HttpSession session) {

        if (studentService.check(student)) {
            Student loginStud = studentService.findByEmail(student.getEmail());
            session.setAttribute("loggedInUser", loginStud);

            return "redirect:/student/" + loginStud.getId() + "/home";
        } else {
            return "login-error";
        }


       // return studentService.check(student) ? "redirect:/student/" + loginStudent.getId() + "/home" : "login-error";
    }

}
