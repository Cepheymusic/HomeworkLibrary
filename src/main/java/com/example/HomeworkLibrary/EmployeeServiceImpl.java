package com.example.HomeworkLibrary;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employeeMap;
    public EmployeeServiceImpl() {

        this.employeeMap = new HashMap<>();
    }
    public static final int MAX_EMPLOYEE_COUNT = 4;
    @Override
    public Employee addEmployee(String firstName, String lastName, double salary, int department) {
        if (employeeMap.size() == MAX_EMPLOYEE_COUNT) { //проходимся по списку сотрудников
            throw new EmployeeStorageIsFullException("Превышен лимит сотрудников");
        }
        Employee employee = new Employee(firstName, lastName, salary, department);
        String key = firstName + lastName;

        if(employeeMap.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Сотрудник есть");
        }
        employeeMap.put(key,employee);
        return employee;
    }
    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = employeeMap.remove(firstName + lastName);
        if(employee == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employee;
    }
    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = employeeMap.get(firstName + lastName);
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employee;
    }
    @Override
    public Collection<Employee> findAll() {
        return employeeMap.values();
    }
}