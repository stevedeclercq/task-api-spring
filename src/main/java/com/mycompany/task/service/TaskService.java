package com.mycompany.task.service;

import com.mycompany.task.dto.TaskRequestDTO;
import com.mycompany.task.dto.TaskResponseDTO;
import com.mycompany.task.entity.*;
import com.mycompany.task.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toList;

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

    public Task save(TaskRequestDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDone(dto.isDone());
        task.setDescription(dto.getDescription());
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            task.setCategory(category);
        }
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

    private TaskResponseDTO mapToResponseDTO(Task task){
        TaskResponseDTO taskDTO = new TaskResponseDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDone(task.isDone());
        taskDTO.setDescription(task.getDescription());

        if((task.getPriority()!=null)){
            taskDTO.setPriorityName(task.getPriority().getName());
        }
        if((task.getStatus()!=null)) {
            taskDTO.setStatusName(task.getStatus().getName());
        }

        List<String>tags = new ArrayList<>();
        if(task.getTags()!=null){

        for(Tag s : task.getTags()) {
                tags.add(s.getLabel());
            }
        }
        taskDTO.setTags(tags);

        if(task.getCategory()!=null) {
            taskDTO.setCategoryName(task.getCategory().getName());
        }
        return taskDTO;
    }

    public List<TaskResponseDTO> findAllDTO(){
        List<TaskResponseDTO> liste =  repo.findAll().stream().map(this::mapToResponseDTO).toList();

        return liste;
    }

    public Optional<TaskResponseDTO> update(Long id, TaskRequestDTO dto){

         return  repo.findById(id).map(existing -> {
            existing.setDescription(dto.getDescription());
            existing.setDone(dto.isDone());
            existing.setTitle(dto.getTitle());

            if(dto.getCategoryId() !=null) {
                Category category = new Category();
                category.setId(dto.getCategoryId());
                existing.setCategory(category);
            }
             if(dto.getPriorityId() !=null) {
                 Priority priority = new Priority();
                 priority.setId(dto.getPriorityId());
                 existing.setPriority(priority);
             }
             if(dto.getStatusId() !=null) {
                 Status status = new Status();
                 status.setId(dto.getStatusId());
                 existing.setStatus(status);
             }
             if(dto.getTagIds() !=null) {
                 Set<Tag> tags = new HashSet<>();

                 for(Long tagId : dto.getTagIds()){
                     Tag t = new Tag();
                     t.setId(tagId);
                     tags.add(t);
                 }
                 existing.setTags(tags);
             }
            attachRelations(existing);
            Task saved = repo.save(existing) ;
             return mapToResponseDTO(saved);

        });
    }

    public Optional<TaskResponseDTO> findByIdDTO(Long id){
        return repo.findById(id)
                .map(this::mapToResponseDTO);
    }

}
