package com.EmployeeLeaveManagementSystem.demo.Repository;

import com.EmployeeLeaveManagementSystem.demo.Entity.Employee;
import com.EmployeeLeaveManagementSystem.demo.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long > {
    Optional<RefreshToken> findByToken(String token);


}
