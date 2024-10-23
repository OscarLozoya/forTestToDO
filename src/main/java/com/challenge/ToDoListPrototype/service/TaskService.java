package com.challenge.ToDoListPrototype.service;

import com.challenge.ToDoListPrototype.persistence.entity.TaskEntity;
import com.challenge.ToDoListPrototype.persistence.repository.TaskRepository;
import org.hibernate.grammars.hql.HqlParser;
import org.hibernate.type.descriptor.jdbc.LocalDateTimeJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    public final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<TaskEntity> getAllTask(){
        return taskRepository.findAll();
    }

    public List<String> getAllTaskText(){
        List<String> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(task ->tasks.add(formatTaskText(task)));
        return tasks;
    }

    public List<String> getAllTaskText2(){
        List<String> tasks = new ArrayList<>();
        taskRepository.findAllByOrderByIdTaskAsc().forEach(task ->tasks.add(formatTaskText(task)));
        return tasks;
    }

    public List<String> getAllTaskText3(){
        List<String> tasks = new ArrayList<>();
        taskRepository.findAllByOrderByIdTaskDesc().forEach(task ->tasks.add(formatTaskText(task)));
        return tasks;
    }public List<String> getAllTaskText4(){
        List<String> tasks = new ArrayList<>();
        taskRepository.findAllByOrderByCreationDateAsc().forEach(task ->tasks.add(formatTaskText(task)));
        return tasks;
    }

    public List<String> getAllTaskText5(){
        List<String> tasks = new ArrayList<>();
        taskRepository.findAllByOrderByCreationDateDesc().forEach(task ->tasks.add(formatTaskText(task)));
        return tasks;
    }

    public TaskEntity saveTask(TaskEntity taskEntity){
        return taskRepository.save(taskEntity);
    }
    public TaskEntity updateTask(TaskEntity taskEntity){
        taskEntity.setCreationDate(LocalDateTime.now());
        return taskRepository.save(taskEntity);
    }
    public void saveTaskOnList(List<String> tasks){
        List<TaskEntity> tasksToSave = new ArrayList<>();
        tasks.forEach(task -> {
            tasksToSave.add(new TaskEntity(task, LocalDateTime.now()));
        });
        taskRepository.saveAll(tasksToSave);
    }
    //for only one operation per time
    public TaskEntity saveNewTask(String taskDescription){
        TaskEntity task = new TaskEntity();
        task.setDescription(taskDescription);
        task.setCreationDate(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public TaskEntity getTask(int taskId){
       return taskRepository.findById(taskId).orElse(null);
    }

    public boolean deleteTask(int taskId){
        taskRepository.deleteById(taskId);
        return true;
    }

    public boolean taskExist(int taskId){
        return taskRepository.existsById(taskId);
    }
    private String formatTaskText(TaskEntity task){
        return task.getIdTask()+","+task.getDescription()+","+task.getCreationDate();
    }
}
