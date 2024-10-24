package com.challenge.ToDoListPrototype.utils;

import java.util.List;

public class TextPresentationUtils {
    private static final int CONSOLE_LENGTH=100;

    public static void presentDataList(List<String> listData, String separator) {
        //Title and Heads for the data view table
        createConsoleTitle("TO DO LIST");
        System.out.println("|" + String.format("%-6s", "ID") + "|" + String.format("%-70s", "TASK DESCRIPTION") + "|" + String.format("%-20s", "DATE") + "|");
        //Display information in table view format
        if (!listData.isEmpty())
        {
            listData.forEach(row -> {
                String[] columns = row.split(separator);
                System.out.println("|" + String.format("%-6s", columns[0]) + "|" +
                        String.format("%-70s", columns[1]) + "|" + String.format("%-20s", columns[2]) + "|");
            });
        }
        else
           createConsoleTitle("NO DATA");
        singleLineDiv();
    }

    public static void editTaskTemplateText(int currentId,String currentTask){
        System.out.println("Task ID: "+currentId+" Current description:\n" +
                currentTask+
                "\nEnter new description:");
    }

    public static void createConsoleTitle(String title){
        int lengthNeed = CONSOLE_LENGTH - title.length() - 2;// minus 2 for spaces, one in prev and 0ne in pos title
        int leftLength = lengthNeed/2;
        int rightLength = lengthNeed- leftLength;
        String newTitle =String.format("%s %s %s","-".repeat(leftLength), title,"-".repeat(rightLength));
        System.out.println(newTitle);
    }

    public static void presentSummary(String action, String success, String errors){
        createConsoleTitle("SUMMARY OF "+action.toUpperCase());
        System.out.println("Correct "+ action +":"+ success);
        System.out.println("Incorrect or Not found:"+ errors);
        singleLineDiv();
    }

    public static void presentSingleSummary(String action, int success){
        createConsoleTitle("SUMMARY OF "+action.toUpperCase());
        System.out.println("Correct "+ action +":"+ success);
        singleLineDiv();
    }

    public static void singleLineDiv(){
        System.out.printf("%s%n","-".repeat(CONSOLE_LENGTH));
    }
}
