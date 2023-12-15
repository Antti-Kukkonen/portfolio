import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { AuthContextProvider } from './Context/AuthContext';
import { WeatherContextProvider } from './Context/WeatherContext';
import { DropdownContextProvider } from './Context/DropdownContext';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <AuthContextProvider>
      <WeatherContextProvider>
        <DropdownContextProvider>
          <App />
        </DropdownContextProvider>
      </WeatherContextProvider>
    </AuthContextProvider>
  </React.StrictMode>
);