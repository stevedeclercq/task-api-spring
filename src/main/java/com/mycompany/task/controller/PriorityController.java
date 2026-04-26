package com.mycompany.task.controller;

import com.mycompany.task.entity.Priority;
import com.mycompany.task.service.PriorityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/priorities")
public class PriorityController {

    private final PriorityService service;

    public PriorityController(PriorityService service){
        this.service=service;
    }

    @GetMapping
    public List<Priority>findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Priority>findById(@PathVariable Long id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Priority save(@RequestBody Priority priority){
        return service.save(priority);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if(service.deleteById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
