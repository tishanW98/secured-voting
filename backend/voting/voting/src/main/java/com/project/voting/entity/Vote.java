package com.project.voting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Vote {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Election election;

    @ManyToOne
    private User voter;

    private String voteHash;
    private LocalDateTime timestamp;
}
