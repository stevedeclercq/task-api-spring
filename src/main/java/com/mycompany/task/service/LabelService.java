package com.mycompany.task.service;

import com.mycompany.task.entity.Label;
import com.mycompany.task.repository.LabelRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LabelService {

    final private LabelRepository repository;

    public LabelService(LabelRepository repo){
        this.repository=repo;
    }

    public List<Label> findAll(){
        return repository.findAll();
    }

    public Optional<Label> findById(Long id){
        return repository.findById(id);
    }

    public Label create(Label label){
        return repository.save(label);
    }

    public boolean deleteById(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Label> update(Long id, Label incoming){

        return repository.findById(id)
                .map(existing -> {
                    existing.setName(incoming.getName());
                    return repository.save(existing);
                });
    }
}
