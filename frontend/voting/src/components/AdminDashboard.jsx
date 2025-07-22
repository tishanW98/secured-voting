import React, { useState, useEffect } from "react";
import api from "../services/api";
import "../styles/Dashboard.css";

export default function AdminDashboard() {
  const [elections, setElections] = useState([]);
  const [form, setForm] = useState({ title: "", description: "" });
  const [userForm, setUserForm] = useState({
    name: "",
    email: "",
    role: "VOTER",
  });

  useEffect(() => {
    api.get("/admin/elections").then((res) => setElections(res.data));
  }, []);

  const createElection = () => {
    api.post("/admin/elections", form).then(() => alert("Election created"));
  };

  const createUser = () => {
    api.post("/admin/users", userForm).then(() => alert("User created"));
  };

  return (
    <div className="container">
      <h1>Admin Dashboard</h1>

      <h3>Create Election</h3>
      <input
        placeholder="Title"
        onChange={(e) => setForm({ ...form, title: e.target.value })}
      />
      <input
        placeholder="Description"
        onChange={(e) => setForm({ ...form, description: e.target.value })}
      />
      <button onClick={createElection}>Create</button>

      <h3>Create Voter</h3>
      <input
        placeholder="Name"
        onChange={(e) => setUserForm({ ...userForm, name: e.target.value })}
      />
      <input
        placeholder="Email"
        onChange={(e) => setUserForm({ ...userForm, email: e.target.value })}
      />
      <select
        onChange={(e) => setUserForm({ ...userForm, role: e.target.value })}
      >
        <option value="VOTER">VOTER</option>
        <option value="ADMIN">ADMIN</option>
      </select>
      <button onClick={createUser}>Create</button>

      <h3>All Elections</h3>
      <ul>
        {elections.map((e) => (
          <li key={e.id}>{e.title}</li>
        ))}
      </ul>
    </div>
  );
}
