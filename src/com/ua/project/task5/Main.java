package com.ua.project.task5;

import com.ua.project.task5.models.Employee;
import com.ua.project.task5.serialization.Deserialization;
import com.ua.project.task5.serialization.Serialization;
import com.ua.project.util.helpers.InputHelpers;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        boolean isMenuRequired;
        final Path PATH_TO_EMPLOYEES_FILE;
        List<Employee> result = new ArrayList<Employee>();

        PATH_TO_EMPLOYEES_FILE = InputHelpers.getPathFromInput("\n Enter path to file with employees data: ");
        Corporation corporation = Deserialization.deserializeCorporationData(PATH_TO_EMPLOYEES_FILE);

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
                    isMenuRequired = true;

                    while(isMenuRequired) {
                        switch (getSearchMenuChoice()) {
                            case 1:
                                System.out.println();
                                result = corporation.searchEmployeeByLastname(InputHelpers.getStringInputByLength("\n Enter searching lastname: ", 4));
                                break;
                            case 2:
                                System.out.println();
                                result = corporation.searchEmployeeByAge(InputHelpers.getPositiveIntegerInput("\n Enter searching age: "));
                                break;
                            case 3:
                                System.out.println();
                                result = corporation.searchEmployeeByLastnameFirstChar(InputHelpers.getStringInputByLength("\n Enter first letter of lastname to search: ", 1).charAt(0));
                                break;
                            case 0:
                                System.out.println("\n Returning to main menu...");
                                isMenuRequired = false;
                                break;
                            default:
                                System.out.println("\n Incorrect input, please try again!\n");
                                break;
                        }

                        if(!result.isEmpty()) {
                            result.forEach(System.out::println);
                            if(getSaveMenuChoice()) {
                                final Path PATH_TO_FILE = Paths.get("./src/com/ua/project/task5/employeesData/foundEmployeesData.txt");

                               System.out.println("\n Saving data...");
                               saveEmployeeData(result, PATH_TO_FILE, true);
                               System.out.println("\n Data saved to " + PATH_TO_FILE.getFileName() + "\n");
                            }

                            result.clear();
                        }
                    }
                    break;
                case 0:
                    Serialization.serializeData(corporation, PATH_TO_EMPLOYEES_FILE, false);
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
        System.out.println("  5. Search by;");
        System.out.println("  0. Exit;");
        System.out.println("-".repeat(25));

        return InputHelpers.getPositiveIntegerInput(" Enter field: ");
    }

    private static int getSearchMenuChoice() {
        System.out.println("-".repeat(25));
        System.out.println(" Search employee by:");
        System.out.println("  1. Lastname;");
        System.out.println("  2. Age;");
        System.out.println("  3. First letter of lastname;");
        System.out.println("  0. To main menu;");
        System.out.println("-".repeat(25));

        return InputHelpers.getPositiveIntegerInput(" Enter field: ");
    }

    private static boolean getSaveMenuChoice() {
        while(true) {
            switch (InputHelpers.getStringInputByLength("\n Do you want to save found data?(y/n): ", 1).toLowerCase()) {
                case "y" -> {
                    return true;
                }
                case "n" -> {
                    return false;
                }
                default -> {
                    System.out.println("\n Incorrect input, try again!\n");
                }
            }
        }
    }

    private static void saveEmployeeData(final List<Employee> list, final Path pathToSaveData, final boolean isAppend) {
        StringBuilder builder = new StringBuilder();

        try(OutputStream fileWriter = new FileOutputStream(pathToSaveData.toFile(), isAppend)){
            list.forEach((value) -> {
                builder.append(value).append("\n");
            });

            builder.append("-".repeat(15)).append("\n");
            fileWriter.write(builder.toString().getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
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
