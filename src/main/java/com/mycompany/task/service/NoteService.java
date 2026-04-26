package com.mycompany.task.service;

import com.mycompany.task.entity.Note;
import com.mycompany.task.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository repo;

    public NoteService(NoteRepository repo){
        this.repo=repo;
    }

    public List<Note> findAll(){
        return repo.findAll();
    }

    public Optional<Note> findById(Long id){
        return repo.findById(id);
    }

    public Note save(Note note){
        return repo.save(note);
    }

    public void deleteById(Long id){
         repo.deleteById(id);
    }

    public Optional<Note> update(Long id, Note incoming){
        //retourne si une note avec cette id à été trouvé
        return repo.findById(id).map(existing -> {
            //existing contindra une note avec ses valeur = à celle de Note passé en param à la fin de l'execution
            // (au départ note avec ses valeurs de base, sortie de db)
            existing.setContent(incoming.getContent());
            existing.setArchived(incoming.isArchived());
            //on sauve la Note
            return repo.save(existing);
        });


    }
}
