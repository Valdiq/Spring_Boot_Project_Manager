package com.example.springboot_projectmanager.dao;

import com.example.springboot_projectmanager.entity.Project;
import com.example.springboot_projectmanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    public List<Project> findStudentsByProjectList(Student student);

    public Student findByEmailAndPassword(String email, String password);

    public Student findByEmail(String email);

}
