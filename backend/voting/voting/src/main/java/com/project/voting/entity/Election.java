package com.project.voting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Election {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private boolean active;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}