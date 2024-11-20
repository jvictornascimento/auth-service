package com.jvictornascimento.auth_service.controller;

import com.jvictornascimento.auth_service.model.dto.UserRegiterDto;
import com.jvictornascimento.auth_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(name = "/auth")
public class AuthController {
    @Autowired
    private UserService service;

    @PostMapping(name = "register")
    public String register (UserRegiterDto data){
        return service.save(data);

    }


}
