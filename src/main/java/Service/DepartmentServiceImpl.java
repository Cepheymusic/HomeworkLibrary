package Service;

import Dto.Employee;
import Exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;
    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @Override
    public Employee findMaxSalaryByDepartment(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparing(employee -> employee.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найдет"));
    }
    @Override
    public Employee findMinSalaryByDepartment(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparing(employee -> employee.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найдет"));
    }
    @Override
    public Collection<Employee> getAllEmployeeByDepartment(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }
    @Override
    public Map<Integer, List<Employee>> getEmployeeByDepartment() {
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment()));
    }
}
