package com.challenge.ToDoListPrototype.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuDisplayUtils {
    private final Scanner scan = new Scanner(System.in);

    public String displayMainMenu(){

        System.out.println("OPTIONS:");
        System.out.println("Insert Items (Press I)\n" +
                "Edit Item (Press E)\n" +
                "Remove Items (Press R)\n" +
                "Print Items (Press P)\n" +
                "Exit (Press X)\n" +
                "Your selection:");
        return scan.nextLine();
    }

    public String displayYesOrNotMenu(){
        System.out.println("Add another task? Y/N:");
        return scan.nextLine();
    }

    public String displayEditMenu(){
        TextPresentationUtils.createConsoleTitle("EDIT TASK OPTION");
        System.out.println("Id to Edit\n" +
                "(For edit more than One using ',' to separate ids):");
        return scan.nextLine();
    }

    public String displayRemoveMenu(){
        TextPresentationUtils.createConsoleTitle("REMOVE TASK OPTION");
		System.out.println("Id to remove\n" +
				"(For remove more than One using ',' to separate ids):");
        return scan.nextLine();
    }

    public String displayPrintOptions(){
        TextPresentationUtils.createConsoleTitle("SHOW ITEMS OPTIONS");
        System.out.println("Show task order by \n" +
                "Item Id Ascend (Press IA)\n" +
                "Item Id Descend (Press ID)\n"+
                "Date Ascend (Press DA)\n" +
                "Date Descend (Press DD)\n" +
                "Main menu (Press X)");
        return scan.nextLine();
    }
}
