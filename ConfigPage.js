import React, { useState, useEffect } from "react";
import axios from "axios";

const ConfigPage = () => {
  const [formValues, setFormValues] = useState({
    totalTickets: "",
    maxTicketCapacity: "",
    ticketReleaseRate: "",
    customerRetrievalRate: "",
  });
  const [latestConfig, setLatestConfig] = useState(null); // Track the latest configuration
  const [error, setError] = useState("");

  // Fetch the latest configuration from the backend when the component loads
  useEffect(() => {
    const fetchLatestConfig = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/tickets/config");
        setLatestConfig(response.data); // Assuming the response contains the latest config
      } catch (err) {
        console.error("Error fetching latest config:", err);
      }
    };

    fetchLatestConfig();
  }, []); // Empty dependency array ensures it only runs once when the component mounts

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormValues({ ...formValues, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(""); // Clear previous errors

    try {
      const response = await axios.post("http://localhost:8080/api/tickets/config", formValues);
      setLatestConfig(formValues); // Update the latest config after submitting
      setFormValues({
        totalTickets: "",
        maxTicketCapacity: "",
        ticketReleaseRate: "",
        customerRetrievalRate: "",
      }); // Reset the form
      alert(response.data); // Notify success
    } catch (err) {
      setError("Failed to submit configuration. Please check your input or server status.");
    }
  };

  const handleDeleteConfig = () => {
    setLatestConfig(null); // Clear the displayed latest configuration
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gradient-to-br from-gray-100 to-gray-300">
      <div className="w-full max-w-lg p-8 bg-white shadow-lg rounded-lg">
        <h2 className="text-2xl font-bold text-center text-gray-800 mb-6">
          Add Configuration
        </h2>
        <form onSubmit={handleSubmit} className="space-y-6">
          <div>
            <label className="block font-medium text-gray-700">Total Tickets</label>
            <input
              type="number"
              name="totalTickets"
              value={formValues.totalTickets}
              onChange={handleChange}
              className="border rounded w-full p-3 text-gray-700 focus:ring focus:ring-blue-300"
              required
            />
          </div>
          <div>
            <label className="block font-medium text-gray-700">Max Ticket Capacity</label>
            <input
              type="number"
              name="maxTicketCapacity"
              value={formValues.maxTicketCapacity}
              onChange={handleChange}
              className="border rounded w-full p-3 text-gray-700 focus:ring focus:ring-blue-300"
              required
            />
          </div>
          <div>
            <label className="block font-medium text-gray-700">Ticket Release Rate</label>
            <input
              type="number"
              name="ticketReleaseRate"
              value={formValues.ticketReleaseRate}
              onChange={handleChange}
              className="border rounded w-full p-3 text-gray-700 focus:ring focus:ring-blue-300"
              required
            />
          </div>
          <div>
            <label className="block font-medium text-gray-700">Customer Retrieval Rate</label>
            <input
              type="number"
              name="customerRetrievalRate"
              value={formValues.customerRetrievalRate}
              onChange={handleChange}
              className="border rounded w-full p-3 text-gray-700 focus:ring focus:ring-blue-300"
              required
            />
          </div>
          <button
            type="submit"
            className="w-full bg-blue-500 text-white py-3 rounded-lg shadow-md hover:bg-blue-600 focus:ring focus:ring-blue-300"
          >
            Submit
          </button>
        </form>

        {/* Display error message */}
        {error && <p className="text-red-500 text-center mt-4">{error}</p>}

        {/* Display the latest configuration */}
        {latestConfig && (
          <div className="mt-8 p-6 border rounded-lg bg-gray-50 shadow-md">
            <h3 className="text-lg font-semibold text-gray-800 mb-4 text-center">
              Latest Configuration
            </h3>
            <ul className="space-y-2 text-gray-700">
              <li>Total Tickets: {latestConfig.totalTickets}</li>
              <li>Max Ticket Capacity: {latestConfig.maxTicketCapacity}</li>
              <li>Ticket Release Rate: {latestConfig.ticketReleaseRate}</li>
              <li>Customer Retrieval Rate: {latestConfig.customerRetrievalRate}</li>
            </ul>
            <button
              onClick={handleDeleteConfig}
              className="mt-6 w-full bg-red-500 text-white py-2 rounded-lg hover:bg-red-600 focus:ring focus:ring-red-300"
            >
              Delete Configuration
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default ConfigPage;
