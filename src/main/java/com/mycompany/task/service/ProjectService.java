package com.mycompany.task.service;

import com.mycompany.task.entity.Project;
import com.mycompany.task.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository repo;

    public ProjectService(ProjectRepository repo){
        this.repo=repo;
    }

    public List<Project> findAll(){
        return repo.findAll();
    }

    public Optional<Project> findById(Long id){
        return repo.findById(id);
    }

    public Project save(Project project){
        return repo.save(project);
    }

    public void deleteById(Long id){
         repo.deleteById(id);
    }
}
