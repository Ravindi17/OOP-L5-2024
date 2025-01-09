import React from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <nav className="bg-blue-600 p-4">
      <div className="container mx-auto flex justify-between">
        <h1 className="text-white text-lg font-bold">Ticketing System</h1>
        <div>
          <Link to="/config" className="text-white mx-2">
            Configuration
          </Link>
          <Link to="/dashboard" className="text-white mx-2">
            Dashboard
          </Link>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
