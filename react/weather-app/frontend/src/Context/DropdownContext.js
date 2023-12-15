import { createContext, useReducer } from 'react'

export const DropdownContext = createContext()

export const dropdownReducer = (state, action) => {
  switch (action.type) {
    case 'TOGGLE_DROPDOWN': 
      return {
        open: !state.open
      }
    default:
      throw new Error()
  }
}

export const DropdownContextProvider = ({ children }) => {
  const [state, dispatch] = useReducer(dropdownReducer, {
    open: false
  })

  return (
    <DropdownContext.Provider value={{...state, dispatch}}>
      { children }
    </DropdownContext.Provider>
  )
}