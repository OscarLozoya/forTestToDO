package com.challenge.ToDoListPrototype.app;

import com.challenge.ToDoListPrototype.persistence.entity.TaskEntity;
import com.challenge.ToDoListPrototype.service.TaskService;
import com.challenge.ToDoListPrototype.utils.InputInfoUtils;
import com.challenge.ToDoListPrototype.validation.InputValidation;
import com.challenge.ToDoListPrototype.utils.MenuDisplayUtils;
import com.challenge.ToDoListPrototype.utils.TextPresentationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class MainApp {
    @Autowired
    private TaskService taskService;
    @Autowired
    private MenuDisplayUtils menuDisplay;
    @Autowired
    private InputInfoUtils inputInfo;

    private final Scanner scan = new Scanner(System.in);

    public void menuCommandLine(){

        boolean continueProgram = true;
        do{
            printAllItems();
            String response = menuDisplay.displayMainMenu();
            if(InputValidation.isValidMenuOption(response, InputValidation.MAIN_MENU_REGEX, false))
            {
                switch (response){
                    case "I"://total
                        insertItems();
                        break;
                    case "E"://Total
                        editItems();
                        break;
                    case "R"://total
                        removeItems();
                        break;
                    case "P"://Total
                        printItems();
                        break;
                    case "X"://Total
                        continueProgram=false;
                        break;
                }
            }
        }while(continueProgram);
    }
    //TODO MEJORAR EL NOMBRE Y EL LUGAR
    public void printAllItems(){
        TextPresentationUtils.presentDataList( taskService.getAllTask(), ",");
    }

    //Method to manage insertions, uses a Do while cycle to admit multiple insertions with interactive message to continue
    public void insertItems(){
        boolean insertMore = true;
        String responseMenu;
        List<String> tasksList = new ArrayList<>();
        TextPresentationUtils.createConsoleTitle("ADD NEW TASKS for main appI");
        String newTask = inputInfo.addTaskDescription();
        tasksList.add(newTask);
        do{
            responseMenu = menuDisplay.displayYesOrNotMenu();
            if(InputValidation.isValidMenuOption(responseMenu,InputValidation.YN_MENU_REGEX,  false)){
                switch(responseMenu){
                    case "Y":
                        newTask = inputInfo.addTaskDescription();
                        tasksList.add(newTask);
                        break;
                    case "N":
                        insertMore = false;
                        break;
                }
            }
        }while(insertMore);
        taskService.saveTaskOnList(tasksList);
        TextPresentationUtils.presentSingleSummary("Task Saved", tasksList.size());
    }

    //Method to Allowed Edit Items, first if exists id get information and modify date and description
	public  void editItems(){
		List<String> errorsId = new ArrayList<>();
		String taskIds = menuDisplay.displayEditMenu();
		List<Integer> correctIds = InputValidation.validFormatTaskId(taskIds, errorsId);
        List<Integer> successId = new ArrayList<>();
		List<TaskEntity> taskToEdit = taskService.getTasksById(correctIds,successId);
		taskToEdit.forEach(task -> {
			TextPresentationUtils.editTaskTemplateText(task.getIdTask(),task.getDescription());
			task.setDescription(scan.nextLine());
			task.setDate(LocalDateTime.now());
           // successId.add(task.getIdTask());
		});
        //Save Edited Items
		taskService.saveTaskList(taskToEdit);
        //Show Results
        correctIds.removeAll(successId);
        correctIds.forEach(id -> errorsId.add(id.toString()));
        TextPresentationUtils.presentSummary("Edits", successId.toString(), errorsId.toString());

	}


    //Logic to Remove Items
    public void removeItems(){
        String taskIds = menuDisplay.displayRemoveMenu();
        List<String> errors = new ArrayList<>();
        List<Integer> ids = InputValidation.validFormatTaskId(taskIds, errors);
        ids=taskService.deleteTasks(ids,errors);
        TextPresentationUtils.presentSummary("Removes",ids.toString(), errors.toString());
    }

    //TODO ADD description
    public void printItems(){
        String responseMenu;
        boolean printMenu = true;
        do{
            responseMenu = menuDisplay.displayPrintOptions();
            if(InputValidation.isValidMenuOption( responseMenu, InputValidation.MENU_PRINT_REGEX, false)){
                switch(responseMenu){
                    case "IA":
                        TextPresentationUtils.presentDataList( taskService.getAllTaskOrderByIdAsc(), ",");
                        break;
                    case "ID":
                        TextPresentationUtils.presentDataList( taskService.getAllTaskText3(), ",");
                        break;
                    case "DA":
                        TextPresentationUtils.presentDataList( taskService.getAllTaskText4(), ",");
                        break;
                    case "DD":
                        TextPresentationUtils.presentDataList( taskService.getAllTaskText5(), ",");
                        break;
                    case "X":
                        printMenu=false;
                        break;
                }
            }

        }while(printMenu);
    }


}
