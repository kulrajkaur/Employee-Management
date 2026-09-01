package com.EmployeeLeaveManagementSystem.demo.Service;

import com.EmployeeLeaveManagementSystem.demo.Entity.Employee;
import com.EmployeeLeaveManagementSystem.demo.Repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService{
private final EmployeeRepository employeeRepository;
public EmployeeService(EmployeeRepository employeeRepository){
    this.employeeRepository= employeeRepository;}
// add employee//
public Employee addEmployee(Employee employee){
return employeeRepository.save(employee);
}
//list employees//
public Page<Employee> listEmployee(Pageable pageable){
    return employeeRepository.findAll(pageable);
}
//delete employee//
    public void deleteEmployee(Long employeeId){
    employeeRepository.findById(employeeId)
            .orElseThrow(()->new RuntimeException("Employee not found"));
    employeeRepository.deleteById(employeeId);
    }
}


