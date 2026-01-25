package com.mycompany.task.controller;

import com.mycompany.task.entity.Tag;
import com.mycompany.task.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController{

    private final TagService service;

    public TagController(TagService service){
        this.service=service;
    }

    @GetMapping
    public List<Tag> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> findById(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tag>create(@RequestBody Tag tag){
        Tag saved = service.save(tag);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        boolean deleted = service.deleteById(id);

        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
