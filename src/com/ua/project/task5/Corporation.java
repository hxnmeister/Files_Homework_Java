package com.ua.project.task5;

import com.ua.project.task5.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class Corporation {
    private List<Employee> employees;

    public Corporation(List<Employee> employees) {
        this.employees = employees;
    }
    public Corporation() {
        this(new ArrayList<Employee>(List.of(
                new Employee(31, "Oleksa", "Kilovich"),
                new Employee(23, "Ivan", "Ivanov"),
                new Employee(43, "Oleg", "Olegov", "Olegovich")
        )));
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public boolean push(final Employee employee) {
        return this.employees.add(employee);
    }

    public void editEmployee(final int employeeIndex) {
        employees.get(employeeIndex).editBio();
    }

    public Employee deleteEmployee(final int index) {
        if(index < 0 || index > this.employees.size()){
            return null;
        }

        return this.employees.remove(index);
    }

    public List<Employee> searchEmployeeByLastname(final String lastName) {
        return this.employees.stream().filter((employee) -> employee.getLastName().equalsIgnoreCase(lastName)).toList();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\n Employees:\n" + "-".repeat(20) + "\n");
        this.employees.forEach((employee) -> builder.append(employee).append("\n"));
        builder.append("-".repeat(20)).append("\n");

        return builder.toString();
    }
}
