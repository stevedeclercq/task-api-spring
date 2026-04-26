package com.mycompany.task.controller;

import com.mycompany.task.entity.Status;
import com.mycompany.task.service.StatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/statuses")
@RestController
public class StatusController {

    private final StatusService service;

    public StatusController(StatusService service){
        this.service=service;
    }

    @GetMapping
    public List<Status>findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status>findById(@PathVariable Long id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Status save(@RequestBody Status status){
        return service.save(status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteById(@PathVariable Long id){
        if(service.deleteById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
