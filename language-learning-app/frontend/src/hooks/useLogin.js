import { useState } from "react";
import { useAuthContext } from "./useAuthContext"

export const useLogin = () => {
    const [error, setError] = useState(null)
    const [isLoading, setIsLoading] = useState(null)
    const { dispatch } = useAuthContext()
    let backendUrl = "http://127.0.0.1:5000"

    const login = async (email, password) => {
        setIsLoading(true)
        setError(null)

        const response = await fetch(`${backendUrl}/api/user/login`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({email, password})
        })
        const json = await response.json()

        if (!response.ok) {
            setIsLoading(false)
            setError(json.error)
        }
        if (response.ok) {
            localStorage.setItem("user", JSON.stringify(json))

            dispatch({type: "LOGIN", playload: json})

            setIsLoading(false)
        }
    }
    return { login, isLoading, error }
}