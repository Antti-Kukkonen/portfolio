import './App.css';
import Navbar from './Components/Navbar/Navbar';
import Weather from './Components/Weather/Weather';
import SignupPage from './Components/Signup/SignupPage';
import LoginPage from './Components/Login/LoginPage';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <Router>
      <Navbar/>
        <Routes>
          <Route
            path="/"
            element={<Weather />}
          />
          <Route
          path="/signup"
          element={<SignupPage />} 
        />
           <Route
          path="/login"
          element={<LoginPage />} 
        />
        </Routes>
    </Router>
  );
}



export default App;
