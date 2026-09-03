package com.EmployeeLeaveManagementSystem.demo.Controller;

import com.EmployeeLeaveManagementSystem.demo.Entity.Leave;
import com.EmployeeLeaveManagementSystem.demo.Entity.LeaveStatus;
import com.EmployeeLeaveManagementSystem.demo.Entity.LeaveType;
import com.EmployeeLeaveManagementSystem.demo.Service.LeaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;



@RestController
@RequestMapping("/leave")
public class LeaveController {
    private final LeaveService leaveService;
    public LeaveController(LeaveService leaveService){
        this.leaveService=leaveService;
    }
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @PostMapping("/apply-leave")
    public Leave applyLeave(@RequestParam Long employeeId, @RequestParam LeaveType leaveType, @RequestParam String reason, @RequestParam MultipartFile file, @RequestParam LocalDate leaveDate)throws IOException {
        System.out.println("CONTROLLER CALLED");
        return leaveService.applyLeave(employeeId, leaveType, reason, file, leaveDate);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/reject/{leaveId}")
    public Leave rejectLeave(@PathVariable Long leaveId){
        return leaveService.rejectLeave(leaveId);
    }

    @PutMapping("/cancel/{leaveId}")
    public Leave cancelLeave(@PathVariable Long leaveId){
        return leaveService.cancelLeave(leaveId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/approve/{leaveId}")
    public Leave approveLeave(@PathVariable Long leaveId){
        return leaveService.approveLeave(leaveId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list")
    public Page<Leave> showList(@RequestParam(required = false) LeaveStatus leaveStatus,
                                @RequestParam int page,
                                @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size);

        return leaveService.showList(leaveStatus, pageable);
    }
    }

