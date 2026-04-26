package com.mycompany.task.service;

import com.mycompany.task.entity.*;
import com.mycompany.task.repository.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskService {

    private final TaskRepository repo;
    private final CategoryRepository categoryRepository;
    private final PriorityRepository priorityRepository;
    private final StatusRepository statusRepository;
    private final TagRepository tagRepository;

    public TaskService(TaskRepository repo,
                       CategoryRepository categoryRepository,
                       PriorityRepository priorityRepository,
                       StatusRepository statusRepository,
                       TagRepository tagRepository) {
        this.repo = repo;
        this.categoryRepository = categoryRepository;
        this.priorityRepository = priorityRepository;
        this.statusRepository = statusRepository;
        this.tagRepository = tagRepository;
    }

    public void tri(){
        repo.findAll().stream().filter(Task::isDone).filter(t->t.getPriority().getLevel()>1).forEachOrdered(task -> {task.getPriority();});
    }
    public List<Task> findAll(){

        return repo.findAll();
    }

    public Optional<Task> findById(Long id){
        return repo.findById(id);
    }

    public Task save(Task task){
        attachRelations(task);
        return repo.save(task);
    }

    public void deleteById(Long id){
        repo.deleteById(id);
    }

    public Optional<Task> update(Long id, Task incoming) {
        return repo.findById(id).map(existing -> {
            existing.setTitle(incoming.getTitle());
            existing.setDescription(incoming.getDescription());
            existing.setDone(incoming.isDone());
            existing.setCategory(incoming.getCategory());
            existing.setPriority(incoming.getPriority());
            existing.setStatus(incoming.getStatus());
            existing.setTags(incoming.getTags());

            attachRelations(existing);
            return repo.save(existing);
        });
    }

    private void attachRelations(Task task) {
        if (task.getCategory() != null && task.getCategory().getId() != null) {
            Category category = categoryRepository.findById(task.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            task.setCategory(category);
        }

        if (task.getPriority() != null && task.getPriority().getId() != null) {
            Priority priority = priorityRepository.findById(task.getPriority().getId())
                    .orElseThrow(() -> new RuntimeException("Priority not found"));
            task.setPriority(priority);
        }

        if (task.getStatus() != null && task.getStatus().getId() != null) {
            Status status = statusRepository.findById(task.getStatus().getId())
                    .orElseThrow(() -> new RuntimeException("Status not found"));
            task.setStatus(status);
        }

        if(task.getTags()!=null){
            if(task.getTags().isEmpty()){
                task.setTags(new HashSet<>());
            }else{
                Set<Long> ids = new HashSet<>();
                for(Tag tag : task.getTags()){
                    ids.add(tag.getId()); }
                Set<Tag> managedTags = new HashSet<>(tagRepository.findAllById(ids));
                task.setTags(managedTags);}}
    }

}
