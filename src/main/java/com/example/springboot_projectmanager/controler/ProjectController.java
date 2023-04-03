package com.example.springboot_projectmanager.controler;

import com.example.springboot_projectmanager.entity.Project;
import com.example.springboot_projectmanager.entity.Student;
import com.example.springboot_projectmanager.service.impl.ProjectServiceImpl;
import com.example.springboot_projectmanager.service.impl.StudentServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private StudentServiceImpl studentService;

    @RequestMapping("/")
    public String showAddPage(Model model) {

        model.addAttribute("project", new Project());

        return "add-project";
    }

    @PostMapping("/add-project")
    public String addProject(@Valid @ModelAttribute("project") Project project, BindingResult result, HttpSession session) {

        if (result.hasErrors()) {
            return "add-project";
        } else {
            Student student = (Student) session.getAttribute("loggedInUser");

            student = studentService.findById(student.getId());

            student.addProject(project);
            project.addStudent(student);

            projectService.saveProject(project);

            return "redirect:/student/" + student.getId() + "/home";
        }

    }

    @GetMapping("/show-view")
    public String showView(@RequestParam("id") int id, Model model) {

        Project project = projectService.getById(id);

        model.addAttribute("project", project);


        return "project-view";
    }

    @RequestMapping("/start")
    public String startProject(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {


        Project project = projectService.getById(id);

        if (project.getStatus().equals("Pending")) {
            project.setStatus("In Progress");
            projectService.saveProject(project);
        } else if (project.getStatus().equals("Completed") || project.getStatus().equals("In Progress")) {
            System.out.println("Already In Progress or Completed");
        }

        redirectAttributes.addAttribute("id", id);

        return "redirect:/project/show-view";
    }

    @RequestMapping("/finish")
    public String finishProject(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {


        Project project = projectService.getById(id);

        if (project.getStatus().equals("In Progress")) {
            project.setStatus("Completed");
            projectService.saveProject(project);
        } else if (project.getStatus().equals("Completed") || project.getStatus().equals("Pending")) {
            System.out.println("Already Completed or Pending");
        }

        redirectAttributes.addAttribute("id", id);

        return "redirect:/project/show-view";
    }

    @GetMapping("/filter")
    public String filterProject(@RequestParam(name = "prName", required = false) String prName, @RequestParam(name = "prStatus", required = false) String prStatus, HttpSession session) {

        System.out.println(prName);
        System.out.println(prStatus);


        Student currentStudent = (Student) session.getAttribute("loggedInUser");
        currentStudent = studentService.findById(currentStudent.getId());

        //List<Project> projectList = projectService.findAllByNameAndStatus(prName, prStatus);
        List<Project> projectList = projectService.findAllByStatus(prStatus);
        currentStudent.setProjectList(projectList);

        session.setAttribute("loggedInUser", currentStudent);

        return "redirect:/student/" + currentStudent.getId() + "/home";
    }

    @RequestMapping("/show-edit")
    public String showEditPage(@RequestParam("id") int id, Model model) {

        Project project = projectService.getById(id);

        model.addAttribute("project", project);

        return "project-edit";
    }


    @RequestMapping("/edit")
    public String editProject(@RequestParam("name") String name, @RequestParam("description") String description, @Valid @ModelAttribute("project") Project project, BindingResult result, HttpSession session) {

        if (result.hasErrors()) {
            return "project-edit";
        } else {
            Student currStud = (Student) session.getAttribute("loggedInUser");
            currStud = studentService.findById(currStud.getId());

            Project currProject = projectService.getById(project.getId());

            currProject.setName(name);
            currProject.setDescription(description);
            currProject.setStatus(currProject.getStatus());

            projectService.saveProject(currProject);


            return "redirect:/student/" + currStud.getId() + "/home";
        }


    }

    @RequestMapping("/delete")
    public String deleteProject(@RequestParam("id") int id, HttpSession session) {
        Student currStud = (Student) session.getAttribute("loggedInUser");
        currStud = studentService.findById(currStud.getId());

        Project project = projectService.getById(id);

        projectService.deleteById(project.getId());

        return "redirect:/student/" + currStud.getId() + "/home";
    }


}
