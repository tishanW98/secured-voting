import React from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Home.css";

function Home() {
  const navigate = useNavigate();

  return (
    <div className="home-container">
      <h1 className="home-title">Secure Class Voting</h1>
      <p className="home-subtitle">Choose your role to continue:</p>
      <div className="button-group">
        <button onClick={() => navigate("/admin")} className="admin-button">
          Admin Dashboard
        </button>
        <button onClick={() => navigate("/voter")} className="voter-button">
          Voter Dashboard
        </button>
      </div>
    </div>
  );
}

export default Home;
