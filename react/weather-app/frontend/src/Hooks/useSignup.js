import { useState } from "react";
import { useAuthContext } from "./useAuthContext";

export const useSignup = () => {
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(null);
  const { dispatch } = useAuthContext();
  let backendUrl = ''
  if (process.env.NODE_ENV === 'production') {
    backendUrl = 'https://weather-app-backend-kl5w.onrender.com'
  }

  const signup = async (email, password) => {
    setIsLoading(true);
    setError(null);

    const response = await fetch(`${backendUrl}/api/users`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password }),
    });
    const user = await response.json();

    if (!response.ok) {
      setError(user.message);
      setIsLoading(false);
      return (error);
    }

    sessionStorage.setItem("user", JSON.stringify(user));
    dispatch({ type: "LOGIN", payload: user });
    setIsLoading(false);
  };

  return { signup, isLoading, error };
};
