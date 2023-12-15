import { useAuthContext } from "./useAuthContext"

export const useFavorites = () => {
  const { user } = useAuthContext()
  let backendUrl = ''
  if (process.env.NODE_ENV === 'production') {
    backendUrl = 'https://weather-app-backend-kl5w.onrender.com'
  }

  const addFavorite = async (locationName, latitude, longitude) => {
    if (!user) return
    const response = await fetch(`${backendUrl}/api/favorites`, {
      method: 'POST',
      headers: {
        'Authorization': `Bearer ${user.token}`,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ locationName, latitude, longitude })
    })
    const json = await response.json()

    if (!response.ok) {
      throw new Error(json.message)
    }
  }

  const removeFavorite = async (id) => {
    if (!user) return
    const response = await fetch(`${backendUrl}/api/favorites/${id}`, {
      method: 'DELETE',
      headers: {
        'Authorization': `Bearer ${user.token}`,
      }
    })
    const json = await response.json()

    if (!response.ok) {
      throw new Error(json.message)
    }
  }

  const getFavorites = async () => {
    if (!user) return
    const response = await fetch(`${backendUrl}/api/favorites`,
    {
      headers: {
        'Authorization': `Bearer ${user.token}`,
      }
    })
    const json = await response.json()

    if (!response.ok) {
      throw new Error(json.message)
    }

    return json[0]
  }

  return { addFavorite, removeFavorite, getFavorites }
}