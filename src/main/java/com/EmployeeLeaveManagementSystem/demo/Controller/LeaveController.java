package com.EmployeeLeaveManagementSystem.demo.Controller;

import com.EmployeeLeaveManagementSystem.demo.Entity.Leave;
import com.EmployeeLeaveManagementSystem.demo.Entity.LeaveType;
import com.EmployeeLeaveManagementSystem.demo.Service.LeaveService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/leave")
public class LeaveController {
    private final LeaveService leaveService;
    public LeaveController(LeaveService leaveService){
        this.leaveService=leaveService;
    }
    @PostMapping("/apply-leave")
    public Leave applyLeave(@RequestParam Long employeeId, @RequestParam LeaveType leaveType, @RequestParam String reason, @RequestParam MultipartFile file, @RequestParam LocalDate leaveDate)throws IOException {
        System.out.println("CONTROLLER CALLED");
        return leaveService.applyLeave(employeeId, leaveType, reason, file, leaveDate);
    }
    @PutMapping("/reject/{leaveId}")
    public Leave rejectLeave(@PathVariable Long leaveId){
        return leaveService.rejectLeave(leaveId);
    }
    @PutMapping("/accept/{leaveId}")
    public Leave acceptLeave(@PathVariable Long leaveId){
        return leaveService.acceptLeave(leaveId);
    }
    @PutMapping("/cancel/{leaveId}")
    public Leave cancelLeave(@PathVariable Long leaveId){
        return leaveService.cancelLeave(leaveId);
    }
}
