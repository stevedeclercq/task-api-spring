package com.mycompany.task.controller;

import com.mycompany.task.entity.Category;
import com.mycompany.task.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service){
        this.service=service;
    }

    @GetMapping
    public List<Category> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Category save(@RequestBody Category category){
        return service.save(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category>update( @PathVariable Long id, @RequestBody Category category){
        return service.update(id, category).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
