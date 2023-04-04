package com.example.springboot_projectmanager.service.impl;

import com.example.springboot_projectmanager.dao.ProjectRepository;
import com.example.springboot_projectmanager.dao.StudentRepository;
import com.example.springboot_projectmanager.entity.Project;
import com.example.springboot_projectmanager.entity.Student;
import com.example.springboot_projectmanager.service.ProjectService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Project> getAllProjects() {
        List<Project> projectList = projectRepository.findAll();

        return projectList;
    }

    @Override
    public Project getById(int id) {
        Project project = null;

        Optional<Project> optional = projectRepository.findById(id);

        if (optional.isPresent()) {
            project = optional.get();
        }

        return project;
    }

    @Override
    public List<Student> getAllStudentsForProject(Project project) {
        List<Student> studentList = projectRepository.getAllByStudentList(project);

        return studentList;
    }

    @Override
    public List<Project> getAllByStatus(String status) {
        List<Project> projectList = projectRepository.getAllByStatus(status);

        return projectList;
    }

    @Override
    public void saveProject(Project project) {

        if (project.getStatus() == null) {
            project.setStatus("Pending");
        }

        projectRepository.save(project);
    }

    @Override
    public void deleteById(int id) {
        Project project = getById(id);

        project.setStudentList(null);

        projectRepository.deleteById(id);
    }


    @Override
    public List<Project> findAllByStatusIgnoreCase(String status) {

        List<Project> projectList = projectRepository.findAllByStatusIgnoreCase(status);

        return projectList;
    }

    @Override
    public List<Project> findAllByNameLikeIgnoreCase(String name) {

        List<Project> projectList = projectRepository.findAllByNameLikeIgnoreCase(name);

        return projectList;
    }

/*    @Override
    public List<Project> findAllByNameAndStatus(String name, String status) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
        Root<Project> root = criteriaQuery.from(Project.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }

        if (status != null && !status.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("status"), status));
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[0])).orderBy(criteriaBuilder.asc(root.get("name")));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }*/
}
