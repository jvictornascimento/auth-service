package com.jvictornascimento.auth_service.service;

import com.jvictornascimento.auth_service.model.UsersModel;
import com.jvictornascimento.auth_service.model.dto.UserRegiterDto;
import com.jvictornascimento.auth_service.model.enuns.Roles;
import com.jvictornascimento.auth_service.reposity.UsersRepository;
import com.jvictornascimento.auth_service.service.exception.UserEmailException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UsersRepository repository;

    public String save(UserRegiterDto data) {
        if (repository.existsByEmail(data.email())) {
            throw new UserEmailException("Email já existente!");
        }
        UsersModel user = new UsersModel();
        BeanUtils.copyProperties(data, user);
        user.setPassword(new BCryptPasswordEncoder().encode(data.password()));
        user.setRole(Roles.USER);
        user.setCreated_at(new Timestamp(System.currentTimeMillis()));
        repository.save(user);
        return "Usuario salvo com sucesso";
    }
}