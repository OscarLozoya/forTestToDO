package com.challenge.ToDoListPrototype;

import com.challenge.ToDoListPrototype.application.commandApp;
import com.challenge.ToDoListPrototype.persistence.entity.TaskEntity;
import com.challenge.ToDoListPrototype.service.TaskService;
import com.challenge.ToDoListPrototype.utils.InputValidation;
import com.challenge.ToDoListPrototype.utils.TextPresentationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import org.w3c.dom.Text;

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
				case "I"://Parcial
					insertItems();
					break;
				case "E":
					editItems();
					break;
				case "R":
					removeItems();
					break;
				case "P"://Parcial
					printItems();
					break;
				case "X":
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

		System.out.println("Task List description:"+tasksList.toString());
		taskService.saveTaskOnList(tasksList);
		System.out.println("Tasks Saved");
		/*System.out.println("Task description:");
		String task = scan.nextLine();
		String saveResponse= taskService.saveNewTask(task).toString();
		System.out.println(saveResponse);
*/
	}

	public  void editItems(){
		System.out.println("Task Id to Edit:");
		String taskId = scan.nextLine();
		int tskId = Integer.parseInt(taskId);
		TaskEntity result =  taskService.getTask(tskId);
		if(result!= null){
			TextPresentationUtils.editTaskTemplateText(result.getDescription());
			String newDescription = scan.nextLine();
			result.setDescription(newDescription);
			taskService.updateTask(result);
		}
		

	}

	public void removeItems(){
		System.out.println("Ingrese el numero de la tarea a Eliminar:");
		//TODO Agregar validaciones para solo aceptar numero luego preguntar si desea salir o continuar
		String taskId = scan.nextLine();
		taskService.deleteTask(Integer.parseInt(taskId));
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
