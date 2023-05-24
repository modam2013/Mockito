package com.example.mockito.service;

import com.example.mockito.exception.DepartmentNotFoundException;
import com.example.mockito.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public double maxSalaryFromDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .mapToDouble(Employee::getSalary)
                .max()
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public double minSalaryFromDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .mapToDouble(Employee::getSalary)
                .min()
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public double sumSalaryFromDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    public List<Employee> employeesFromDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> employeesGroupedByDepartment() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

}
