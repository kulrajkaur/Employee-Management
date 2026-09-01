package com.EmployeeLeaveManagementSystem.demo.Service;

import com.EmployeeLeaveManagementSystem.demo.Entity.Employee;
import com.EmployeeLeaveManagementSystem.demo.Entity.Leave;
import com.EmployeeLeaveManagementSystem.demo.Entity.LeaveStatus;
import com.EmployeeLeaveManagementSystem.demo.Entity.LeaveType;
import com.EmployeeLeaveManagementSystem.demo.Repository.EmployeeRepository;
import com.EmployeeLeaveManagementSystem.demo.Repository.LeaveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

@Service
public class LeaveService {
    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;
    public LeaveService(LeaveRepository leaveRepository, EmployeeRepository employeeRepository){
        this.leaveRepository=leaveRepository;
        this.employeeRepository=employeeRepository;
    }
    @Transactional
    public Leave applyLeave(Long employeeId, LeaveType leaveType, String reason, MultipartFile file, LocalDate leaveDate) throws IOException {
        if(file== null || file.isEmpty()){
            throw new RuntimeException("File is required");
        }
        System.out.println("File name: " + file.getOriginalFilename());
        System.out.println("Content type: " + file.getContentType());
        if(file.getSize()> 5*1024*1024){
            throw new RuntimeException("Size must be 5MB only");
        }
        String fileName = file.getOriginalFilename();
        if (fileName == null ||
                (!fileName.toLowerCase().endsWith(".jpg")
                        && !fileName.toLowerCase().endsWith(".jpeg")
                        && !fileName.toLowerCase().endsWith(".png"))) {

            throw new RuntimeException("Only JPG and PNG files are allowed");
        }

        Employee employee=employeeRepository.findById(employeeId)
                .orElseThrow(()->new RuntimeException("Employee Id not found"));
        String uploadDir= "uploads/";
        File directory= new File(uploadDir);
        if(!directory.exists()){
            directory.mkdir();
        }

        Path filePath= Paths.get(uploadDir + fileName);
        Files.copy(file.getInputStream(),
                filePath, StandardCopyOption.REPLACE_EXISTING);
        Leave leave = new Leave();
        leave.setEmployee(employee);
        leave.setLeaveType(leaveType);
        leave.setReason(reason);
        leave.setFileName(file.getOriginalFilename());
        leave.setLeaveValue(leaveType.getValue());
        leave.setLeaveStatus(LeaveStatus.PENDING);
        leave.setLeaveDate(leaveDate);
        return leaveRepository.save(leave);
    }
    public Leave rejectLeave(Long leaveId){
        Leave leave= leaveRepository.findById(leaveId)
                .orElseThrow(()->new RuntimeException("Leave not found"));
        leave.setLeaveStatus(LeaveStatus.REJECTED);
        return leaveRepository.save(leave);
    }
    public Leave acceptLeave(Long leaveId){
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(()->new RuntimeException("Leave not found"));
        leave.setLeaveStatus(LeaveStatus.APPROVED);
        return leaveRepository.save(leave);
    }
    public Leave cancelLeave(Long leaveId){
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(()->new RuntimeException("Leave not found"));
        leave.setLeaveStatus(LeaveStatus.CANCELLED);
        return leaveRepository.save(leave);
    }
}
