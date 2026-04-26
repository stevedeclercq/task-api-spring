package com.mycompany.task.service;

import com.mycompany.task.entity.Priority;
import com.mycompany.task.repository.PriorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriorityService {

    private final PriorityRepository repository;

    public PriorityService(PriorityRepository repository){
        this.repository=repository;
    }

    public List<Priority>findAll(){
        return repository.findAll();
    }

    public Optional<Priority> findById(Long id){
        return repository.findById(id);
    }

    public Priority save(Priority priority){
        return repository.save(priority);
    }

    public boolean deleteById(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}

