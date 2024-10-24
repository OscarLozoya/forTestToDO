package com.challenge.ToDoListPrototype;

import com.challenge.ToDoListPrototype.persistence.entity.TaskEntity;
import com.challenge.ToDoListPrototype.service.TaskService;
import com.challenge.ToDoListPrototype.utils.InputValidation;
import com.challenge.ToDoListPrototype.utils.TextPresentationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ToDoListPrototypeApplication implements CommandLineRunner {

	public static Scanner scan;
	@Autowired
	private TaskService taskService;

	public static void main(String[] args) {

		SpringApplication.run(ToDoListPrototypeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menuCommandLine();
	}

	public void menuCommandLine(){
		scan = new Scanner(System.in);
		int option = 1;
		do{
			printAllItems();
			System.out.println("OPTIONS:");
			System.out.println("Insert Items (Press I)\n" +
					"Edit Item (Press E)\n" +
					"Remove Items (Press R)\n" +
					"Print Items (Press P)\n" +
					"Exit (Press X)\n" +
					"Your selection:");
			String response = scan.nextLine();
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
						option=0;
						break;
				}
			}
		}while(option != 0);
		scan.close();
	}

	public void insertItems(){
		boolean insertMore = true;
		String responseMenu;
		List<String> tasksList = new ArrayList<>();
		TextPresentationUtils.createConsoleTitle("ADD NEW TASKS");
		System.out.println("Task description:");
		tasksList.add(scan.nextLine());
		do{
			System.out.println("Add another task? Y/N:");
			responseMenu = scan.nextLine();
			if(InputValidation.isValidMenuOption(responseMenu,InputValidation.YN_MENU_REGEX,false)){
				switch(responseMenu){
					case "Y":
						System.out.println("Task description:");
						tasksList.add(scan.nextLine());
						break;
					case "N":
						insertMore = false;
						break;
				}
			}
		}while(insertMore);
		taskService.saveTaskOnList(tasksList);
		System.out.println("Tasks Saved");
	}

	public  void editItems(){
		String taskIds;
		List<String> errorsId = new ArrayList<>();
		TextPresentationUtils.createConsoleTitle("EDIT TASK OPTION");
		System.out.println("Id to Edit\n" +
				"(For edit more than One using ',' to separate ids):");
		taskIds = scan.nextLine();
		List<Integer> correctIds = InputValidation.validFormatTaskId(taskIds, errorsId);
		List<TaskEntity> taskToEdit= taskService.getTaskById(correctIds);
		taskToEdit.forEach(task -> {
			TextPresentationUtils.editTaskTemplateText(task.getIdTask(),task.getDescription());
			//String newDescription =
			task.setDescription(scan.nextLine());
			task.setCreationDate(LocalDateTime.now());
		});
		taskService.saveTaskList(taskToEdit);

	}

	public void removeItems(){
		/*?System.out.println("Ingrese el numero de la tarea a Eliminar:");
		//TODO Agregar validaciones para solo aceptar numero luego preguntar si desea salir o continuar
		String taskId = scan.nextLine();
		taskService.deleteTask(Integer.parseInt(taskId));*/
		TextPresentationUtils.createConsoleTitle("REMOVE TASK OPTION");
		System.out.println("Id to remove\n" +
				"(For remove more than One using ',' to separate ids):");
		String response = scan.nextLine();
		List<String> errors = new ArrayList<>();
		List<Integer> ids = InputValidation.validFormatTaskId(response, errors);
		ids=taskService.deleteTasks(ids,errors);
		System.out.println("Correct Deleted:"+ ids.toString());
		System.out.println("Incorrect or Not found:"+ errors.toString());


	}



	public void printItems(){
		String responseMenu;
		boolean printMenu = true;
		do{
			TextPresentationUtils.createConsoleTitle("SHOW ITEMS OPTIONS");
			System.out.println("Show task order by \n" +
					"Item Id Ascend (Press IA)\n" +
					"Item Id Descend (Press ID)\n"+
					"Date Ascend (Press DA)\n" +
					"Descend (Press DD)\n" +
					"Main menu (Press X)");
			responseMenu = scan.nextLine();
			if(InputValidation.isValidMenuOption( responseMenu, InputValidation.MENU_PRINT_REGEX, false)){
				switch(responseMenu){
					case "IA":
						TextPresentationUtils.presentDataList( taskService.getAllTaskText2(), ",");
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

	public void printAllItems(){
		TextPresentationUtils.presentDataList( taskService.getAllTaskText(), ",");
	}


}
