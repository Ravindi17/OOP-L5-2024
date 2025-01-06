import React, { useState } from "react";
import axios from "axios";

const DashboardPage = () => {
  const [status, setStatus] = useState("Not Started");
  const [tickets, setTickets] = useState(0);

  const startSystem = async () => {
    try {
      const response = await axios.post("http://localhost:8080/api/tickets/start");
      setStatus(response.data);
    } catch (error) {
      alert("Error starting system.");
    }
  };

  const stopSystem = async () => {
    try {
      const response = await axios.post("http://localhost:8080/api/tickets/stop");
      setStatus(response.data);
    } catch (error) {
      alert("Error stopping system.");
    }
  };

  const fetchStatus = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/tickets/status");
      setTickets(response.data);
    } catch (error) {
      alert("Error fetching status.");
    }
  };

  return (
    <div className="container mx-auto my-4">
      <h2 className="text-xl font-bold mb-4">Dashboard</h2>
      <p>Status: {status}</p>
      <p>Tickets Available: {tickets}</p>
      <div className="space-x-4 my-4">
        <button onClick={startSystem} className="bg-green-600 text-white p-2">
          Start
        </button>
        <button onClick={stopSystem} className="bg-red-600 text-white p-2">
          Stop
        </button>
        <button onClick={fetchStatus} className="bg-blue-600 text-white p-2">
          Refresh Status
        </button>
      </div>
    </div>
  );
};

export default DashboardPage;
