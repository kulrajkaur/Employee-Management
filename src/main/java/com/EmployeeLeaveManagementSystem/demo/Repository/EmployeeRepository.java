package com.EmployeeLeaveManagementSystem.demo.Repository;

import com.EmployeeLeaveManagementSystem.demo.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
