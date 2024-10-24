package com.challenge.ToDoListPrototype.service;

import com.challenge.ToDoListPrototype.persistence.entity.TaskEntity;
import com.challenge.ToDoListPrototype.persistence.repository.TaskRepository;
import org.hibernate.grammars.hql.HqlParser;
import org.hibernate.type.descriptor.jdbc.LocalDateTimeJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TaskService {
    public final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public void saveTaskList(List<TaskEntity> tasks){
        taskRepository.saveAll(tasks);
    }

    public void saveTaskOnList(List<String> tasks){
        List<TaskEntity> tasksToSave = new ArrayList<>();
        tasks.forEach(task -> {
            tasksToSave.add(new TaskEntity(task, LocalDateTime.now()));
        });
        taskRepository.saveAll(tasksToSave);
    }

    public List<Integer> deleteTasks(List<Integer> ids,List<String> notFoud){
        List<Integer> success = new ArrayList<>();
        List<Integer> toDelete = new ArrayList<>();
        HashMap<Integer,Integer> indexOperation = createVirtualIndexForOperation();
        ids.forEach(id-> {
           if (indexOperation.containsKey(id)){
               if (!taskExist(indexOperation.get(id)))
                   notFoud.add(String.valueOf(id));
               else {
                   success.add(id);
                   toDelete.add(indexOperation.get(id));
               }
           }
           else
               notFoud.add(String.valueOf(id));
        });
       // taskRepository.deleteAllById(ids);
        if(!toDelete.isEmpty())
            taskRepository.deleteAllById(toDelete);
        return success;
    }

    public List<TaskEntity> getTasksById(List<Integer> ids, List<Integer> virtualId){
        HashMap<Integer,Integer> indexOperation = createVirtualIndexForOperation();
        List<Integer> findIds = new ArrayList<>();
        ids.forEach(id-> {
            if (indexOperation.containsKey(id) && taskExist(indexOperation.get(id))){
                findIds.add(indexOperation.get(id));
                virtualId.add(id);
            }
        });
        return taskRepository.findAllById(findIds);
    }

    public boolean taskExist(int taskId){
        return taskRepository.existsById(taskId);
    }

    /**
     *For Operation is needed real taskId on DB so create an Index with
     * key as Virtual Index item and value as real ID task
     */
    private HashMap<Integer, Integer> createVirtualIndexForOperation(){
        HashMap<Integer,Integer> virtualIndex = new HashMap<>();
        List<TaskEntity> tasks = taskRepository.findAll();
        for(int i=0, j=1; i < tasks.size(); i++, j++)
            virtualIndex.put(j,tasks.get(i).getIdTask());
        return virtualIndex;

    }
    /**
     *For Display the user see task begin 1 to task in db
     * so this method create a Virtual Index with
     * key as id task in DB and value as Virtual num begin in one
     */
    private HashMap<Integer, Integer> createVirtualIndexForDisplay(){
        HashMap<Integer,Integer> virtualIndex = new HashMap<>();
        List<TaskEntity> tasks = taskRepository.findAll();
        for(int i=0, j=1; i < tasks.size(); i++, j++)
            virtualIndex.put(tasks.get(i).getIdTask(),j);
        return virtualIndex;

    }

    //Methods to convert data in one String as a Json Row id, description, date for presentation layer
    private String formatTaskText(HashMap<Integer,Integer> index,TaskEntity task){
        //use virtual index for display in order task begin in 0ne
        return index.get(task.getIdTask())+","+task.getDescription()+","+task.getDate();
    }
    private String formatTaskText(TaskEntity task){
        return task.getIdTask()+","+task.getDescription()+","+task.getDate();
    }

    public List<String> getAllTask(){
        List<String> tasks = new ArrayList<>();
        HashMap<Integer,Integer> index = createVirtualIndexForDisplay();
        taskRepository.findAll().forEach(task ->tasks.add(formatTaskText(index,task)));
        return tasks;
    }

    public List<String> getAllTaskOrderByIdAsc(){
        List<String> tasks = new ArrayList<>();
        HashMap<Integer,Integer> indexDisplay = createVirtualIndexForDisplay();
        taskRepository.findAllByOrderByIdTaskAsc().forEach(task ->tasks.add(formatTaskText(indexDisplay,task)));
        return tasks;
    }

    public List<String> getAllTaskText3(){
        List<String> tasks = new ArrayList<>();
        HashMap<Integer,Integer> index = createVirtualIndexForDisplay();
        taskRepository.findAllByOrderByIdTaskDesc().forEach(task ->tasks.add(formatTaskText(index,task)));
        return tasks;
    }
    public List<String> getAllTaskText4(){
        List<String> tasks = new ArrayList<>();
        taskRepository.findAllByOrderByDateAsc().forEach(task ->tasks.add(formatTaskText(task)));
        return tasks;
    }

    public List<String> getAllTaskText5(){
        List<String> tasks = new ArrayList<>();
        taskRepository.findAllByOrderByDateDesc().forEach(task ->tasks.add(formatTaskText(task)));
        return tasks;
    }

    //Single operations first Version
    public TaskEntity saveTask(TaskEntity taskEntity){
        return taskRepository.save(taskEntity);
    }
    public TaskEntity updateTask(TaskEntity taskEntity){
        taskEntity.setDate(LocalDateTime.now());
        return taskRepository.save(taskEntity);
    }

    public TaskEntity saveNewTask(String taskDescription){
        TaskEntity task = new TaskEntity();
        task.setDescription(taskDescription);
        task.setDate(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public TaskEntity getTask(int taskId){
        return taskRepository.findById(taskId).orElse(null);
    }

    public boolean deleteTask(int taskId){
        taskRepository.deleteById(taskId);
        return true;
    }
}
