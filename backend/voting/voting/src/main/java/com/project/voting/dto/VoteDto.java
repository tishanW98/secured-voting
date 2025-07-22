package com.project.voting.dto;

import lombok.Data;

@Data
public class VoteDto {
    private Long electionId;
    private String encryptedVote;
}