package com.example.springboot_projectmanager.service;

import com.example.springboot_projectmanager.entity.Project;
import com.example.springboot_projectmanager.entity.Student;

import java.util.List;

public interface ProjectService {

    public List<Project> getAllProjects();

    public Project getById(int id);

    public List<Student> getAllStudentsForProject(Project project);

    public List<Project> getAllByStatus(String status);

    public void saveProject(Project project);

    public void deleteById(int id);

    public List<Project> findAllByStatus(String status);

    public List<Project> findAllByNameLikeIgnoreCase(String name);

    public List<Project> findAllByNameAndStatus(String name, String status);

}
