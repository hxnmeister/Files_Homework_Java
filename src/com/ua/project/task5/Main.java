package com.ua.project.task5;

import com.ua.project.task5.models.Employee;
import com.ua.project.util.helpers.InputHelpers;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Corporation corporation = new Corporation();

        System.out.println(" Welcome in Corporation!");

        while(true){
            final int choice = getMainMenuChoice();

            switch(choice) {
                case 1:
                    corporation.push(Employee.createEmployeeWithBio());
                    break;
                case 2:
                    corporation.editEmployee(getEmployeeChoice(corporation.getEmployees()) - 1);
                    break;
                case 3:
                    corporation.deleteEmployee(getEmployeeChoice(corporation.getEmployees()) - 1);
                    break;
                case 4:
                    System.out.println(corporation);
                    break;
                case 5:
                    System.out.println();
                    corporation.searchEmployeeByLastname(InputHelpers.getStringInputByLength("\n Enter lastname: ", 4)).forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("\n Closing app...");
                    return;
                default:
                    System.out.println("\n Incorrect value \"" + choice + "\", please try again!\n");
                    break;
            }
        }
    }

    private static int getMainMenuChoice() {
        System.out.println("-".repeat(25));
        System.out.println(" Choose an action:");
        System.out.println("  1. Add employee;");
        System.out.println("  2. Edit employee;");
        System.out.println("  3. Delete employee;");
        System.out.println("  4. Show employees;");
        System.out.println("  5. Search by lastname;");
        System.out.println("  0. Exit;");
        System.out.println("-".repeat(25));

        return InputHelpers.getPositiveIntegerInput(" Enter field: ");
    }

    private static int getEmployeeChoice(final List<Employee> employeeList) {
        int choice;

        System.out.println("\n Available employees:");
        for (int i = 0; i < employeeList.size(); i++) {
            System.out.println((i + 1) + ". " + employeeList.get(i));
            System.out.println("-".repeat(20));
        }

        while(true) {
            choice = InputHelpers.getPositiveIntegerInput(" Enter field: ");

            if(choice > employeeList.size()){
                System.out.println("\n Out of range, try again!");
                continue;
            }

            return choice;
        }
    }
}
