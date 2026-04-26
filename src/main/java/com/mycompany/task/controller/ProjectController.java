package com.mycompany.task.controller;

import com.mycompany.task.entity.Project;
import com.mycompany.task.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service){
        this.service=service;
    }

    @GetMapping
    public List<Project>findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project>findById(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Project save(@RequestBody Project project){
        return service.save(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if(service.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
