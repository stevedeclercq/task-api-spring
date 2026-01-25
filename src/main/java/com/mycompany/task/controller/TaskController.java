package com.mycompany.task.controller;

import com.mycompany.task.entity.Task;
import com.mycompany.task.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById (@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task){
        return ResponseEntity.ok(service.save(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(service.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task) {

        return service.findById(id)
                .map(existing -> {
                    task.setId(id);               // on écrase l'id
                    return ResponseEntity.ok(
                            service.save(task)    // save = update ici
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
