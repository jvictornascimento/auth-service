package com.jvictornascimento.auth_service.controller;


import com.jvictornascimento.auth_service.infra.security.TokenService;
import com.jvictornascimento.auth_service.model.UsersModel;
import com.jvictornascimento.auth_service.model.dto.AuthenticationDTO;
import com.jvictornascimento.auth_service.model.dto.LoginResponseDTO;
import com.jvictornascimento.auth_service.model.dto.UserRegiterDto;
import com.jvictornascimento.auth_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()

public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService service;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRegiterDto data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(data));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((UsersModel) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
