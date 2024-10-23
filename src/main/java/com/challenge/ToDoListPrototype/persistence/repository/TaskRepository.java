package com.challenge.ToDoListPrototype.persistence.repository;

import com.challenge.ToDoListPrototype.persistence.entity.TaskEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface TaskRepository extends ListCrudRepository<TaskEntity, Integer> {
}
