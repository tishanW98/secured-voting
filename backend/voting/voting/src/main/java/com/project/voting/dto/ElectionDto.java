package com.project.voting.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ElectionDto {
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

