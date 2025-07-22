package com.project.voting.controller;

import com.project.voting.dto.ElectionDto;
import com.project.voting.dto.UserCreationDto;
import com.project.voting.service.ElectionService;
import com.project.voting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ElectionService electionService;
    private final UserService userService;

    @PostMapping("/elections")
    public ResponseEntity<?> createElection(@RequestBody ElectionDto dto) {
        return ResponseEntity.ok(electionService.createElection(dto));
    }

    @GetMapping("/elections")
    public ResponseEntity<?> getAllElections() {
        return ResponseEntity.ok(electionService.getAll());
    }

    @GetMapping("/results/{electionId}")
    public ResponseEntity<?> getResults(@PathVariable Long electionId) {
        return ResponseEntity.ok(electionService.getResults(electionId));
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserCreationDto dto) {
        return ResponseEntity.ok(userService.createUser(dto.getName(), dto.getEmail(), dto.getRole()));
    }
}


