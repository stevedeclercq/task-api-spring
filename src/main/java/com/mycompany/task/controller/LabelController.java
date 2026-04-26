package com.mycompany.task.controller;

import com.mycompany.task.entity.Label;
import com.mycompany.task.service.LabelService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labels")
public class LabelController {

    private final LabelService service;

    public LabelController(LabelService service){
        this.service=service;
    }

    @GetMapping
    public List<Label>findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Label> findById(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Label create(@Valid @RequestBody Label label){
        return service.create(label);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if(service.deleteById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Label> update(@PathVariable Long id,@Valid @RequestBody Label label){
        return service.update( id, label)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
