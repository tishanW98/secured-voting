import React, { useState, useEffect } from "react";
import api from "../services/api";
import '../styles/Dashboard.css'; 

export default function VoterPage() {
  const [elections, setElections] = useState([]);
  const [selectedElection, setSelectedElection] = useState(null);
  const [candidateId, setCandidateId] = useState("");
  const [userId, setUserId] = useState("");
  const [voteHash, setVoteHash] = useState("");

  useEffect(() => {
    api.get("/voter/elections").then((res) => setElections(res.data));
  }, []);

  const vote = () => {
    api
      .post(`/voter/vote?userId=${userId}`, {
        electionId: selectedElection,
        candidateId: candidateId,
      })
      .then(() => alert("Vote cast successfully"));
  };

  const fetchHash = () => {
    api
      .get(`/voter/vote-hash/${selectedElection}?userId=${userId}`)
      .then((res) => setVoteHash(res.data));
  };

  return (
    <div className="container">
      <h1>Voter Page</h1>

      <input
        placeholder="Your User ID"
        onChange={(e) => setUserId(e.target.value)}
      />

      <h3>Select Election</h3>
      <select onChange={(e) => setSelectedElection(e.target.value)}>
        <option value="">--Select--</option>
        {elections.map((e) => (
          <option key={e.id} value={e.id}>
            {e.title}
          </option>
        ))}
      </select>

      <input
        placeholder="Candidate ID"
        onChange={(e) => setCandidateId(e.target.value)}
      />
      <button onClick={vote}>Cast Vote</button>

      <h3>Check Your Vote Hash</h3>
      <button onClick={fetchHash}>Get Hash</button>
      {voteHash && <p>Hash: {voteHash}</p>}
    </div>
  );
}
