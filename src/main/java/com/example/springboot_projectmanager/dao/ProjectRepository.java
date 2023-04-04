package com.example.springboot_projectmanager.dao;

import com.example.springboot_projectmanager.entity.Project;
import com.example.springboot_projectmanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    public List<Student> getAllByStudentList(Project project);

    public List<Project> getAllByStatus(String status);

    // @Query(value = "SELECT * FROM projects ORDER BY ?1", nativeQuery = true)
    // public List<Project> findAllAndOrderBy(String order);

    public List<Project> findAllByStatusIgnoreCase(String status);

    public List<Project> findAllByNameLikeIgnoreCase(String name);

    //   public List<Project> findAllByNameAndStatus(String name, String status);

}
