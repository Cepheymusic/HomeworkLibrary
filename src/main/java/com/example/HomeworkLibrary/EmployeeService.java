package com.example.HomeworkLibrary;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, double salary, int department);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> findAll();
}