package com.EmployeeLeaveManagementSystem.demo.Controller;

import com.EmployeeLeaveManagementSystem.demo.Entity.Employee;
import com.EmployeeLeaveManagementSystem.demo.Service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }
    @GetMapping("/list")
    public Page<Employee> listEmployee(@RequestParam int page, @RequestParam int size){
        Pageable pageable= PageRequest.of(page,size);
        return employeeService.listEmployee(pageable);
    }
    @DeleteMapping("/delete/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId){
        employeeService.deleteEmployee(employeeId);
    }
}
