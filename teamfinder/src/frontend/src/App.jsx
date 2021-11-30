import React ,{ Component } from "react";
import "./App.scss";
import { Accounts } from "./components/accounts/accounts";
import { Profile } from "./components/profile/profile";
import { Edit } from "./components/profile/edit";
import { Login, Register } from "./components/login/index";
import {
  BrowserRouter as Router,
  Routes,
  Route
} from 'react-router-dom'
import Dashboard from "./components/dashboard/dashboard";

function App() {

  return (
    <div className="App">
    <Router>
      <Routes>
        <Route path="/" element={<Dashboard/>} />
        <Route path="/accounts" element={<Accounts/>} />
        <Route path="/profile/:id" element={<Profile/>} />
        <Route path="/edit" element={<Edit/>} />

      </Routes>
    </Router>

    </div>
  );
}

export default App;