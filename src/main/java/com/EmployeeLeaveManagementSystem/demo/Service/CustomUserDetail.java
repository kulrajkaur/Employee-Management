package com.EmployeeLeaveManagementSystem.demo.Service;

import com.EmployeeLeaveManagementSystem.demo.Entity.Employee;
import com.EmployeeLeaveManagementSystem.demo.Repository.EmployeeRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetail implements UserDetailsService {
    private final EmployeeRepository employeeRepository;
    public CustomUserDetail(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String employeeId)  throws UsernameNotFoundException{
        Long id = Long.parseLong(employeeId);
        Employee employee= employeeRepository.findById(id)
                .orElseThrow(()->new UsernameNotFoundException("Employee Id not found"));
        return User.builder()
                .username(employee.getEmployeeId().toString())
                .password(employee.getPassword())
                .authorities(employee.getRole())
                .build();
    }
}
