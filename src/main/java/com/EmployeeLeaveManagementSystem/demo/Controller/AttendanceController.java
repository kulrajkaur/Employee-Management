package com.EmployeeLeaveManagementSystem.demo.Controller;

import com.EmployeeLeaveManagementSystem.demo.Entity.Attendance;
import com.EmployeeLeaveManagementSystem.demo.Service.AttendanceService;
import com.EmployeeLeaveManagementSystem.demo.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;
    private final EmployeeService employeeService;
    public AttendanceController(AttendanceService attendanceService, EmployeeService employeeService){
        this.attendanceService=attendanceService;
        this.employeeService=employeeService;
    }
    @PostMapping("/check-in")
    public Attendance checkIn(@RequestParam Long employeeId){
        return attendanceService.checkIn(employeeId);
    }
    @PostMapping("/check-out")
    public Attendance checkOut(@RequestParam Long attendanceId, @RequestParam String description){
        return attendanceService.checkOut(attendanceId, description);
    }
    @GetMapping("/list")
    public List<Attendance> findAttendance(@RequestParam Long employeeId){
        return attendanceService.findAttendance(employeeId);
    }
}
