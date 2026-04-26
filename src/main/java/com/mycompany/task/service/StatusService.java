package com.mycompany.task.service;

import com.mycompany.task.entity.Status;
import com.mycompany.task.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

    private final StatusRepository repo;

    public StatusService(StatusRepository repo){
        this.repo=repo;
    }

    public List<Status>findAll(){
        return repo.findAll();
    }

    public Optional<Status>findById(Long id){
        return repo.findById(id);
    }

    public Status save(Status status){
        return repo.save(status);
    }

    public boolean deleteById(Long id){
        if(repo.existsById(id)){
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
