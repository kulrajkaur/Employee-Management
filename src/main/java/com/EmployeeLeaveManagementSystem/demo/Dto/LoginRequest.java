package com.EmployeeLeaveManagementSystem.demo.Dto;

public class LoginRequest {
    private Long employeeId;
    private String password;
    public LoginRequest(Long employeeId, String password){
        this.employeeId=employeeId;
        this.password=password;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
