package com.project.voting.controller;

import com.project.voting.dto.VoteDto;
import com.project.voting.entity.User;
import com.project.voting.service.UserService;
import com.project.voting.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/voter")
@RequiredArgsConstructor
public class VoterController {
    private final VoteService voteService;
    private final UserService userService;

    @GetMapping("/elections")
    public ResponseEntity<?> getActiveElections() {
        return ResponseEntity.ok(voteService.getActiveElections());
    }

    @PostMapping("/vote")
    public ResponseEntity<?> castVote(@RequestBody VoteDto dto, @RequestParam Long userId) {
        return ResponseEntity.ok(voteService.castVote(dto, userId));
    }

    @GetMapping("/vote-hash/{electionId}")
    public ResponseEntity<?> getVoteHash(@PathVariable Long electionId, @RequestParam Long userId) {
        return ResponseEntity.ok(voteService.getVoteHashForUser(electionId, userId));
    }

    @GetMapping("/user-details/{userId}")
    public ResponseEntity<?> getUserDetails(@PathVariable Long userId) {
        return ResponseEntity.ok(voteService.getUserDetails(userId));
    }
}