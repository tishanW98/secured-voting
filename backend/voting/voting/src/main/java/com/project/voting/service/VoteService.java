package com.project.voting.service;

import com.project.voting.dto.VoteDto;
import com.project.voting.entity.Election;
import com.project.voting.entity.User;
import com.project.voting.entity.Vote;
import com.project.voting.repository.ElectionRepository;
import com.project.voting.repository.UserRepository;
import com.project.voting.repository.VoteRepository;
import com.project.voting.util.CryptoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepo;
    private final ElectionRepository electionRepo;
    private final UserRepository userRepo;

    public List<Election> getActiveElections() {
        return electionRepo.findAll().stream()
                .filter(Election::isActive)
                .collect(Collectors.toList());
    }

    public Vote castVote(VoteDto dto, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (voteRepo.existsByVoterAndElectionId(user, dto.getElectionId())) {
            throw new RuntimeException("Already voted");
        }
        Election e = electionRepo.findById(dto.getElectionId())
                .orElseThrow(() -> new RuntimeException("Election not found"));
        String hash = CryptoUtils.hash(dto.getEncryptedVote());
        Vote v = new Vote();
        v.setElection(e);
        v.setVoter(user);
        v.setVoteHash(hash);
        v.setTimestamp(LocalDateTime.now());
        return voteRepo.save(v);
    }

    public String getVoteHashForUser(Long electionId, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return voteRepo.findByVoterAndElectionId(user, electionId)
                .map(Vote::getVoteHash)
                .orElse("No vote found");
    }

    public UserDetailsDto getUserDetails(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Vote> votes = voteRepo.findAll().stream()
                .filter(vote -> vote.getVoter().getId().equals(userId))
                .collect(Collectors.toList());
        List<UserDetailsDto.VoteInfo> voteInfos = votes.stream()
                .map(vote -> new UserDetailsDto.VoteInfo(
                        vote.getElection().getId(),
                        vote.getElection().getTitle(),
                        vote.getVoteHash(),
                        vote.getTimestamp()))
                .collect(Collectors.toList());
        return new UserDetailsDto(user.getId(), user.getName(), user.getEmail(), user.getRole(), voteInfos);
    }

    // DTO for user details with votes
    public static class UserDetailsDto {
        private Long id;
        private String name;
        private String email;
        private String role;
        private List<VoteInfo> votes;

        public UserDetailsDto(Long id, String name, String email, String role, List<VoteInfo> votes) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.role = role;
            this.votes = votes;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getRole() {
            return role;
        }

        public List<VoteInfo> getVotes() {
            return votes;
        }

        public static class VoteInfo {
            private Long electionId;
            private String electionTitle;
            private String voteHash;
            private LocalDateTime timestamp;

            public VoteInfo(Long electionId, String electionTitle, String voteHash, LocalDateTime timestamp) {
                this.electionId = electionId;
                this.electionTitle = electionTitle;
                this.voteHash = voteHash;
                this.timestamp = timestamp;
            }

            public Long getElectionId() {
                return electionId;
            }

            public String getElectionTitle() {
                return electionTitle;
            }

            public String getVoteHash() {
                return voteHash;
            }

            public LocalDateTime getTimestamp() {
                return timestamp;
            }
        }
    }
}