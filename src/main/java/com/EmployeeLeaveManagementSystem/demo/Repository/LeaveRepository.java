package com.EmployeeLeaveManagementSystem.demo.Repository;

import com.EmployeeLeaveManagementSystem.demo.Entity.Leave;
import com.EmployeeLeaveManagementSystem.demo.Entity.LeaveStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LeaveRepository extends JpaRepository<Leave,Long> {
    @Query("""
            SELECT s From Leave s WHERE s.leaveStatus =:leaveStatus
            """)
    Page<Leave> findByStatus(@Param("leaveStatus") LeaveStatus leaveStatus, Pageable pageable);
}
