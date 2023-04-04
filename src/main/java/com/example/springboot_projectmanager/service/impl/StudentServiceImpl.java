package com.example.springboot_projectmanager.service.impl;

import com.example.springboot_projectmanager.dao.StudentRepository;
import com.example.springboot_projectmanager.entity.Project;
import com.example.springboot_projectmanager.entity.Student;
import com.example.springboot_projectmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository repository;

    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList = repository.findAll();

        return studentList;
    }

    @Override
    public List<Project> getAllProjectsForStudent(Student student) {

        List<Project> projectList = repository.findStudentsByProjectList(student);

        return projectList;
    }

    @Override
    public Student getStudentById(int id) {

        Student student = null;

        Optional<Student> optional = repository.findById(id);

        if (optional.isPresent()) {
            student = optional.get();
        }

        return student;
    }

    @Override
    public List<Project> findStudentsByProjectList(Student student) {
        return null;
    }

    @Override
    public Student findByEmailAndPassword(String email, String password) {

        Student student = repository.findByEmailAndPassword(email, password);

        return student;

    }

    @Override
    public boolean check(Student student) {

        Student checkedStud = findByEmailAndPassword(student.getEmail(), student.getPassword());

        if (checkedStud == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Student findById(int id) {

        Student student = null;

        Optional<Student> optional = repository.findById(id);

        if (optional.isPresent()) {
            student = optional.get();
        }

        return student;
    }

    @Override
    public Student findByEmail(String email) {
        Student student = repository.findByEmail(email);

        return student;
    }

    @Override
    public void saveStudent(Student student) {

        repository.save(student);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);

    }


}
