package com.EmployeeLeaveManagementSystem.demo.Repository;

import com.EmployeeLeaveManagementSystem.demo.Entity.Attendance;
import com.EmployeeLeaveManagementSystem.demo.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    @Query("""
SELECT a FROM Attendance a
WHERE a.employee.employeeId =:employeeId AND a.checkIn BETWEEN :start AND :end
""")
    List <Attendance> findAttendance(
            @Param("employeeId") Long employeeId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);
}
