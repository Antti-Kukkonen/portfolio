import { useState } from 'react'
import { useAuthContext } from './useAuthContext'

export const useLogin = () => {
  const [error, setError] = useState(null)
  const [isLoading, setIsLoading] = useState(null)
  const { dispatch } = useAuthContext()
  let backendUrl = ''
  if (process.env.NODE_ENV === 'production') {
    backendUrl = 'https://weather-app-backend-kl5w.onrender.com'
  }

  const login = async (email, password) => {
    setIsLoading(true)
    setError(null)
    
    const response = await fetch(`${backendUrl}/api/users/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, password })
    })
    const json = await response.json()

    if (!response.ok) {
      setIsLoading(false)
      setError(json.error)
      return (json.error)
    }
    if (response.ok) {
      sessionStorage.setItem('user', JSON.stringify(json))
      dispatch({ type: 'LOGIN', payload: json })
      setIsLoading(false)
    }
  }

  return { login, isLoading, error }
}