import React, { createContext, useReducer, useEffect } from "react"

const initialState = {
    isAuthenticated: false,
    user: null,
    userSettings: { darkMode: false },
}

const mainReducer = (state, action) => {
    switch (action.type) {
        case "LOGIN":
            return { ...state, isAuthenticated: true, user: action.payload }
        case "LOGOUT":
            return { ...state, isAuthenticated: false, user: null }
        default:
            return state
    }
}

export const AppContext = createContext()

export const AppProvider = ({ children }) => {
    const [state, dispatch] = useReducer(mainReducer, initialState)

    useEffect(() => {
        const user = JSON.parse(localStorage.getItem("user"))
        if (user) {
            dispatch({ type: "LOGIN", payload: user })
        }
    }, [])

    return (
        <AppContext.Provider value={{ ...state, dispatch }}>
            {children}
        </AppContext.Provider>
    )
}

export const useAppContext = () => React.useContext(AppContext)
