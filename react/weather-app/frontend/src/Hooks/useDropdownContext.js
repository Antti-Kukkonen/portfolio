import { DropdownContext } from "../Context/DropdownContext"
import { useContext } from "react"

export const useDropdownContext = () => {
  const context = useContext(DropdownContext)

  if(!context) {
    throw Error('useAuthContext must be used inside an DropdownContextProvider')
  }

  return context
}