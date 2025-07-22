package com.project.voting.repository;

import com.project.voting.entity.User;
import com.project.voting.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByVoterAndElectionId(User voter, Long electionId);
    Optional<Vote> findByVoterAndElectionId(User voter, Long electionId);
}

