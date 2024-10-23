package com.challenge.ToDoListPrototype.application;

import com.challenge.ToDoListPrototype.persistence.entity.TaskEntity;
import com.challenge.ToDoListPrototype.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.Scanner;

public class commandApp {
    public static Scanner scan;
    @Autowired
    private static TaskService taskService;
/*
    @Autowired
    public commandApp(TaskService taskService){
        this.taskService = taskService;
    }*/

    public void menuCommandLine(){
        scan = new Scanner(System.in);
        int option = 1;
        do{
            System.out.println("Ingresa una opcion:");
            System.out.println("Insert Items (Press I)\n" +
                    "Edit Item (Press E)\n" +
                    "Remove Items (Press R)\n" +
                    "Print Items (Press P)\n" +
                    "Your selection:");
            String response = scan.nextLine();
            if(response.equals("E"))
                option=0;
            switch (response){
                case "I":
                    insertItems();
                    break;
                case "E":
                    break;
                case "R":
                    break;
                case "P":
                    break;
            }
        }while(option != 0);
    }

    public static void insertItems(){
        System.out.println("Inserte la descripcion de la tarea:");
        String task = scan.nextLine();

    }

    public static void editItems(){

    }

    public static void removeItems(){}

    public static void printItems(){
        System.out.println("Las tareas Son:");
        System.out.println(taskService.getAllTask().toString());
    }
}
