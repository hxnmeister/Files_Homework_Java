package com.ua.project.task5.models;

import com.ua.project.util.helpers.InputHelpers;

import java.io.Serializable;
import java.util.function.Consumer;

public class Employee implements Serializable {
    private int age;
    private String firstName;
    private String lastName;
    private String patronymic;

    public Employee(int age, String firstName, String lastName, String patronymic) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }
    public Employee(int age, String firstName, String lastName) {
        this(age, firstName, lastName, "");
    }
    public Employee() {
        this(20, "FirstName", "LastName");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void fillBio() {
        String tempStr;

        this.setFirstName(InputHelpers.getStringInputByLength("\n Enter first name: ", 3));
        this.setLastName(InputHelpers.getStringInputByLength("\n Enter last name: ", 4));

        tempStr = InputHelpers.getStringInputByLength("\n Enter patronymic (\"0\" to skip): ", 1);
        this.setPatronymic(tempStr.equals("0") ? "" : tempStr);

        this.setAge(InputHelpers.getPositiveIntegerInput("\n Enter age: "));
    }

    private void editStringField(String fieldName, Consumer<String> setter) {
        String tempStr = InputHelpers.getStringInputByLength(" Edit " + fieldName + " (\"0\" to skip): ", 1);

        if(!tempStr.equals("0")) {
            setter.accept(tempStr);
        }
    }

    private void editIntegerField(String fieldName, Consumer<Integer> setter) {
        int tempInt = InputHelpers.getPositiveIntegerInput(" Edit " + fieldName + " (\"0\" to skip): ");

        if(tempInt != 0) {
            setter.accept(tempInt);
        }
    }

    public void editBio() {
        System.out.println("\n Current value: " + this + "\n");

        editStringField("first name", this::setFirstName);
        editStringField("last name", this::setLastName);
        editStringField("patronymic", this::setPatronymic);

        editIntegerField("age", this::setAge);
    }

    public static Employee createEmployeeWithBio() {
        Employee tempEmployee = new Employee();
        tempEmployee.fillBio();

        return tempEmployee;
    }

    @Override
    public String toString() {
        return " " + this.firstName + " " + this.lastName + " " + this.patronymic + " (" + this.age + " years)";
    }
}
