package com.challenge.ToDoListPrototype.persistence.repository;

import com.challenge.ToDoListPrototype.persistence.entity.TaskEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface TaskRepository extends ListCrudRepository<TaskEntity, Integer> {
    List<TaskEntity> findAllByOrderByIdTaskAsc();
    List<TaskEntity> findAllByOrderByIdTaskDesc();
    List<TaskEntity> findAllByOrderByCreationDateAsc();
    List<TaskEntity> findAllByOrderByCreationDateDesc();
}
