package com.project.voting.service;

import com.project.voting.dto.ElectionDto;
import com.project.voting.entity.Election;
import com.project.voting.repository.ElectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ElectionService {
    private final ElectionRepository electionRepo;

    public Election createElection(ElectionDto dto) {
        Election e = new Election();
        e.setTitle(dto.getTitle());
        e.setStartTime(dto.getStartTime());
        e.setEndTime(dto.getEndTime());
        e.setActive(true);
        return electionRepo.save(e);
    }

    public List<Election> getAll() {
        return electionRepo.findAll();
    }

    public Map<String, Long> getResults(Long electionId) {
        // Implement result tally logic
        return Map.of("EncryptedVote1Hash", 12L, "EncryptedVote2Hash", 8L);
    }
}