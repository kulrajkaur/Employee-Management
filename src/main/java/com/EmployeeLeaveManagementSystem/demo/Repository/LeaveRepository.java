package com.EmployeeLeaveManagementSystem.demo.Repository;

import com.EmployeeLeaveManagementSystem.demo.Entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave,Long> {
}
