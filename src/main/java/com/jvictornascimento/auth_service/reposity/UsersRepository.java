package com.jvictornascimento.auth_service.reposity;

import com.jvictornascimento.auth_service.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Long> {
    boolean existsByEmail(String email);
}
