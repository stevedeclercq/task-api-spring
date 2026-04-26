package com.mycompany.task.service;

import com.mycompany.task.entity.Category;
import com.mycompany.task.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repo;

    public CategoryService (CategoryRepository repo){
        this.repo=repo;
    }

    public List<Category> findAll(){
        return repo.findAll();
    }

    public Optional<Category> findById(Long id){
        return repo.findById(id);
    }

    public Category save(Category category){
        return repo.save(category);
    }

    public Optional<Category>update(Long id, Category incoming){
        return repo.findById(id)
                .map(existing-> {
                    existing.setName(incoming.getName());
            return repo.save(existing);
                });
    }
}
