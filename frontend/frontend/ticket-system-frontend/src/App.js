import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar";
import ConfigPage from "./components/ConfigPage";
import DashboardPage from "./components/DashboardPage";

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/config" element={<ConfigPage />} />
        <Route path="/dashboard" element={<DashboardPage />} />
      </Routes>
    </Router>
  );
}

export default App;
