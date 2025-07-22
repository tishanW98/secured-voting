import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import AdminDashboard from "./components/AdminDashboard";
import VoterDashboard from "./components/VoterPage";
import Home from "./components/Home";
// import VotePage from "./pages/VotePage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />

        {/* Voter Routes */}
        <Route path="/voter" element={<VoterDashboard />} />
        {/* <Route path="/vote/:electionId" element={<VotePage />} /> */}

        {/* Admin Routes */}
        <Route path="/admin" element={<AdminDashboard />} />

        {/* Catch-all fallback */}
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </Router>
  );
}

export default App;
