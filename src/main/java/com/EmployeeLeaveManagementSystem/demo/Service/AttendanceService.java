package com.EmployeeLeaveManagementSystem.demo.Service;

import com.EmployeeLeaveManagementSystem.demo.Entity.Attendance;
import com.EmployeeLeaveManagementSystem.demo.Entity.Employee;
import com.EmployeeLeaveManagementSystem.demo.Repository.AttendanceRepository;
import com.EmployeeLeaveManagementSystem.demo.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    public AttendanceService(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository){
        this.attendanceRepository=attendanceRepository;
        this.employeeRepository=employeeRepository;
    }
    //add checkIn time//
    public Attendance checkIn(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        Attendance attendance = new Attendance();

        attendance.setEmployee(employee);
        attendance.setCheckIn(LocalDateTime.now());

        return attendanceRepository.save(attendance);
    }
    //checkOut//
    public Attendance checkOut(Long attendanceId, String description){
        Attendance attendance= attendanceRepository.findById(attendanceId)
                .orElseThrow(()-> new RuntimeException("Employee not found"));
        attendance.setCheckOut(LocalDateTime.now());
        attendance.setDescription(description);
        return attendanceRepository.save(attendance);
    }
    //list attendance//
    public List<Attendance> findAttendance(Long employeeId){
        Employee employee=employeeRepository.findById(employeeId)
                .orElseThrow(()->new RuntimeException("Employee not found"));
        LocalDateTime start=LocalDateTime.now().minusMonths(1);
        LocalDateTime end= LocalDateTime.now();
        return attendanceRepository.findAttendance(employeeId,start,end);
    }
}
