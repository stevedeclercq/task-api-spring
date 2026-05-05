package com.mycompany.task.controller;

import com.mycompany.task.dto.TaskRequestDTO;
import com.mycompany.task.dto.TaskResponseDTO;
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
    public List<TaskResponseDTO> findAll(){
        return service.findAllDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getById (@PathVariable Long id) {
        return service.findByIdDTO(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody TaskRequestDTO dto){
        service.save(dto);
        return ResponseEntity.status(201).body("Task created");
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
    public ResponseEntity<TaskResponseDTO> update(@PathVariable Long id, @RequestBody TaskRequestDTO dto) {
        return service.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
