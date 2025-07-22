package com.project.voting.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String passwordHash;
    private String role;
    private String encryptedPrivateKey;
    private String publicKey;
    private String mfaSecret;
}
