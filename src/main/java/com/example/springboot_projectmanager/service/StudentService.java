package com.example.springboot_projectmanager.service;

import com.example.springboot_projectmanager.entity.Project;
import com.example.springboot_projectmanager.entity.Student;

import java.util.List;

public interface StudentService {

    public List<Student> getAllStudents();

    public List<Project> getAllProjectsForStudent(Student student);

    public Student getStudentById(int id);

    public List<Project> findStudentsByProjectList(Student student);

    public Student findByEmailAndPassword(String email, String password);

    public void saveStudent(Student student);

    public void deleteById(int id);

    public boolean check(Student student);

    public Student findById(int id);

    public Student findByEmail(String email);

}
