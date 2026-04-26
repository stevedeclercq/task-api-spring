package com.mycompany.task.service;

import com.mycompany.task.entity.Tag;
import com.mycompany.task.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService{

    private final TagRepository repo;

    public TagService(TagRepository repo){
        this.repo = repo;
    }

    public List<Tag> findAll(){
        return repo.findAll();
    }

    public Optional<Tag> findById(Long id){
        return repo.findById(id);
    }

    public Tag save(Tag tag){
        return repo.save(tag);
    }

    public boolean deleteById(Long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }

    public Optional<Tag>update(Long id, Tag incoming){
        return repo.findById(id).map
                (existing->
                {existing.setLabel(incoming.getLabel());
        return repo.save(existing);    });
    }
}