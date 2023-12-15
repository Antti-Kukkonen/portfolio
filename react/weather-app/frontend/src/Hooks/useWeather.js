import { useWeatherContext } from './useWeatherContext';

export const useWeather = () => {
  const { dispatch } = useWeatherContext()
  const API_KEY = process.env.REACT_APP_API_KEY;
  const fetchWeatherData = async (query, coord) => {
    try {
      let lat, lon, locationData
      if (!coord) {
        const geo_res = await fetch(`https://api.openweathermap.org/geo/1.0/direct?q=${query}&limit=1&appid=${API_KEY}`)
        locationData = await geo_res.json()
        if (locationData.length === 0) {
          return null
        }
      lat = locationData[0].lat
      lon = locationData[0].lon
      } else {
        const latlon = query.split(', ')
        lat = latlon[0]
        lon = latlon[1]
      }
      if (!lat || !lon) return null
      const weather_res = await fetch(`https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}`)
      const data = await weather_res.json()
      if (data && !coord) {
        data.country = locationData[0].country
        data.state = locationData[0].state
      }
      return data
    } catch (error) {
    console.error('Error fetching weather data:', error);
    }
  }
  const search = async (query, coord=false) => {
    if (!query) return
    const weatherData = await fetchWeatherData(query, coord);
    if (!weatherData) return "No results found"
    dispatch({ type: 'SET_WEATHER', payload: weatherData })
  }
  return { search };
}