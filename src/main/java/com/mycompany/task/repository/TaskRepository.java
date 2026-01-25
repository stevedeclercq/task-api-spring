package com.mycompany.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.task.entity.Task;

public interface
TaskRepository extends JpaRepository<Task, Long> {

}
