package com.challenge.ToDoListPrototype.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InputValidation{
    public static final String MAIN_MENU_REGEX="[IERPX]+";
    public static final String MENU_PRINT_REGEX="^(ID|DD|DA|IA|X)$";
    public static final String YN_MENU_REGEX="[YN]+";

    public static boolean isValidMenuOption(String input,String regex, boolean isNumeric){
            if(input.matches(regex)){
                return true;
            }
            else{
                    System.out.println("Input Error this Menu only accept Uppercase letters and only described in the options menu");
                    return false;
            }
    }

    public static List<Integer> validFormatTaskId(String ids, List<String> errors){
        List<String> auxArray = Arrays.stream(ids.split(",")).toList();
        List<Integer> validIds = new ArrayList<>();
        auxArray.forEach(element -> {
            if(InputValidation.isNumeric(element))
                validIds.add(Integer.parseInt(element));
            else
                errors.add(element);
        });
        return validIds;
    }

    public static boolean isNumeric(String input){
        try{
            Integer.parseInt(input);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean isIndexValid(HashMap<Integer, Integer> index, int value){
        return true;
    }
}
