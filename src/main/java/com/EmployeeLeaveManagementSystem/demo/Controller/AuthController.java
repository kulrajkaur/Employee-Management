package com.EmployeeLeaveManagementSystem.demo.Controller;

import com.EmployeeLeaveManagementSystem.demo.Dto.LoginRequest;
import com.EmployeeLeaveManagementSystem.demo.Dto.LoginResponse;
import com.EmployeeLeaveManagementSystem.demo.Dto.RefreshTokenRequest;
import com.EmployeeLeaveManagementSystem.demo.Entity.RefreshToken;
import com.EmployeeLeaveManagementSystem.demo.Service.JwtService;
import com.EmployeeLeaveManagementSystem.demo.Service.RefreshTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, RefreshTokenService refreshTokenService){
        this.authenticationManager=authenticationManager;
        this.jwtService=jwtService;
        this.refreshTokenService=refreshTokenService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmployeeId().toString(),
                        request.getPassword()
                )
        );
        String token = jwtService.generateToken(
                request.getEmployeeId().toString(),
                authentication.getAuthorities().iterator().next().getAuthority()
        );
        RefreshToken refreshToken= refreshTokenService.generateRefreshToken(request.getEmployeeId());
        return ResponseEntity.ok(new LoginResponse(token, refreshToken.getToken()));
    }
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        RefreshToken refreshToken=refreshTokenService
                .findByToken(refreshTokenRequest.getRefreshToken())
                .orElseThrow(()->new RuntimeException("Token not found"));
        refreshTokenService.verifyToken(refreshToken);
        String accessToken = jwtService.generateToken(
                refreshToken.getEmployee().getEmployeeId().toString(),
                refreshToken.getEmployee().getRole()
        );
        return ResponseEntity.ok(
                new LoginResponse(accessToken, refreshToken.getToken())
        );
    }
}
