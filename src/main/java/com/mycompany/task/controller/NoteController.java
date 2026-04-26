package com.mycompany.task.controller;

import com.mycompany.task.entity.Note;
import com.mycompany.task.service.NoteService;
import org.hibernate.query.sqm.tuple.internal.AnonymousTupleEmbeddableValuedModelPart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service){
        this.service=service;
    }

    @GetMapping
    public List<Note> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> findById(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Note>create(@RequestBody Note note){
        return ResponseEntity.ok(service.save(note));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@PathVariable Long id, @RequestBody Note incoming){
        return service.update(id, incoming)
                //renvoie une réponse 200 si id à été tourvé
                .map(ResponseEntity::ok)
                //sinon constuit une réponse 404
                .orElse(ResponseEntity.notFound().build());
    }
}
