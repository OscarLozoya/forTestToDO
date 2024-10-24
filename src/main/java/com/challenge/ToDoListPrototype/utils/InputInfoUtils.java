package com.challenge.ToDoListPrototype.utils;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputInfoUtils {
    private final Scanner scan = new Scanner(System.in);

    public String addTaskDescription(){
        System.out.println("Task description:");
        return scan.nextLine();
    }
}
