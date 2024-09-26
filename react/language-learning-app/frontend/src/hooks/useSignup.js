import { useState } from "react";
import { useAuthContext } from "./useAuthContext"

export const useSignup = () => {
    const [error, setError] = useState(null)
    const [isLoading, setIsLoading] = useState(null)
    const { dispatch } = useAuthContext()
    let backendUrl = "http://127.0.0.1:5000"

    const signup = async (email, password) => {
        setIsLoading(true)
        setError(null)

        const response = await fetch(`${backendUrl}/api/user/signup`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password })
        })
        const json = await response.json()

        if (!response.ok) {
            setIsLoading(false)
            setError(json.error)
        }

        localStorage.setItem("user", JSON.stringify(json))

        dispatch({ type: "LOGIN", playload: json })

        setIsLoading(false)

    }
    return { signup, isLoading, error }
}