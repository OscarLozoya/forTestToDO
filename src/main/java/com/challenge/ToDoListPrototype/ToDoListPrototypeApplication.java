package com.challenge.ToDoListPrototype;

import com.challenge.ToDoListPrototype.app.MainApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoListPrototypeApplication implements CommandLineRunner {

	@Autowired
	private MainApp mainApp;
	/*public static Scanner scan;
	@Autowired
	private MenuDisplayUtils menuDisplay;
	@Autowired
	private TaskService taskService;*/

	public static void main(String[] args) {
		SpringApplication.run(ToDoListPrototypeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mainApp.menuCommandLine();
		//menuCommandLine();
	}

	//Method for display the Main Menu
	/*public void menuCommandLine(){
		scan = new Scanner(System.in);
		int option = 1;
		do{
			printAllItems();
			//TODO
			//respone = MenuUtils.printMainMenu(scan);
			String response = menuDisplay.displayMainMenu();
			/*System.out.println("OPTIONS:");
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
	}*/

	//Method to manage insertions, uses a Do while cycle to admit multiple insertions with interactive message to continue
	/*public void insertItems(){
		boolean insertMore = true;
		String responseMenu;
		List<String> tasksList = new ArrayList<>();
		TextPresentationUtils.createConsoleTitle("ADD NEW TASKS");
		System.out.println("Task description:");
		tasksList.add(scan.nextLine());
		do{
			responseMenu = menuDisplay.displayYesOrNotMenu();
			/*System.out.println("Add another task? Y/N:");
			responseMenu = scan.nextLine();*/
			//todo
			//responseMunu = printYesNoMenu(scan);
	/*		if(InputValidation.isValidMenuOption(responseMenu,InputValidation.YN_MENU_REGEX,  false)){
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
	}*/

    //Method to Allowed Edit Items, it's possible
	/*public  void editItems(){
		/*
		//TextPresentationUtils.createConsoleTitle("EDIT TASK OPTION");
		String taskIds;
		System.out.println("Id to Edit\n" +
				"(For edit more than One using ',' to separate ids):");
		taskIds = scan.nextLine();*/
	/*	List<String> errorsId = new ArrayList<>();
		String taskIds = menuDisplay.displayEditMenu();
		List<Integer> correctIds = InputValidation.validFormatTaskId(taskIds, errorsId);
		List<TaskEntity> taskToEdit = taskService.getTaskById(correctIds);
		taskToEdit.forEach(task -> {
			TextPresentationUtils.editTaskTemplateText(task.getIdTask(),task.getDescription());
			//String newDescription =
			task.setDescription(scan.nextLine());
			task.setDate(LocalDateTime.now());
		});
		//TODO ADD MESSAGES

		taskService.saveTaskList(taskToEdit);

	}*/

	/*public void removeItems(){

		/*TextPresentationUtils.createConsoleTitle("REMOVE TASK OPTION");
		System.out.println("Id to remove\n" +
				"(For remove more than One using ',' to separate ids):");
		String response = scan.nextLine();
		*/
	/*	String taskIds = menuDisplay.displayRemoveMenu();
		List<String> errors = new ArrayList<>();
		List<Integer> ids = InputValidation.validFormatTaskId(taskIds, errors);
		ids=taskService.deleteTasks(ids,errors);
		System.out.println("Correct Deleted:"+ ids.toString());
		System.out.println("Incorrect or Not found:"+ errors.toString());
	}*/



	/*public void printItems(){
		String responseMenu;
		boolean printMenu = true;
		do{
			responseMenu = menuDisplay.displayPrintOptions();
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
	}*/
/*
	public void printAllItems(){
		TextPresentationUtils.presentDataList( taskService.getAllTaskText(), ",");
	}*/


}
