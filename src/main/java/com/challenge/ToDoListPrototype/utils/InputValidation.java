package com.challenge.ToDoListPrototype.utils;

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
}
