package com.EmployeeLeaveManagementSystem.demo.Service;

import com.EmployeeLeaveManagementSystem.demo.Entity.Employee;
import com.EmployeeLeaveManagementSystem.demo.Entity.RefreshToken;
import com.EmployeeLeaveManagementSystem.demo.Repository.EmployeeRepository;
import com.EmployeeLeaveManagementSystem.demo.Repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${jwt.refresh.expiration}")
    private Long tokenDuration;
    private final RefreshTokenRepository refreshTokenRepository;
    private final EmployeeRepository employeeRepository;
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, EmployeeRepository employeeRepository){
        this.refreshTokenRepository=refreshTokenRepository;
        this.employeeRepository=employeeRepository;
    }
    public RefreshToken generateRefreshToken(Long employeeId){
        Employee employee= employeeRepository.findById(employeeId)
                .orElseThrow(()->new RuntimeException("Employee not found"));
        RefreshToken token=new RefreshToken();
        token.setEmployee(employee);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(LocalDateTime.now().plus(Duration.ofMillis(tokenDuration)));
        return refreshTokenRepository.save(token);
    }
    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }
    public RefreshToken verifyToken(RefreshToken token){
        if(token.getExpiryDate().isBefore(LocalDateTime.now())){
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token expired");
        }
        return token;
    }
}
