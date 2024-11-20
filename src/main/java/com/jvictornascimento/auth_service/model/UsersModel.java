package com.jvictornascimento.auth_service.model;

import com.jvictornascimento.auth_service.model.enuns.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.sql.Timestamp;
@Table(name = "USERS")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UsersModel {
    @Serial
    private static final long serialVersionUID=1l;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotNull
    private String name;
    @Email
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles role;
    @Column(nullable = false)
    private Timestamp created_at;
    private Timestamp update_at;

}
