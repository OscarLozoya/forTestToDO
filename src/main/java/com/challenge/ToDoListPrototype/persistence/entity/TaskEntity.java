package com.challenge.ToDoListPrototype.persistence.entity;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTask;
    @Column(nullable = false, length = 70)
    private String description;
    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime date;

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime creationDate) {
        this.date = creationDate;
    }

    public TaskEntity(){}
    public TaskEntity(String taskDescription, LocalDateTime date){
        this.description = taskDescription;
        this.date = date;
    }
}
